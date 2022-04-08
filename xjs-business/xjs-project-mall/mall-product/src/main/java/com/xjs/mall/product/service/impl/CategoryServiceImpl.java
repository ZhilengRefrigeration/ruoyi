package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.exception.MallException;
import com.xjs.mall.product.dao.CategoryDao;
import com.xjs.mall.product.entity.AttrEntity;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.entity.CategoryBrandRelationEntity;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.AttrGroupService;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.service.CategoryBrandRelationService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.Catelog2Vo;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.xjs.consts.RedisConst.*;


@Service("categoryService")
@Transactional
@Log4j2
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrService attrService;

    @Resource
    private RedissonClient redissonClient;


    @Override
    public List<CategoryEntity> listWithTree() {
        //查缓存
        String cache = stringRedisTemplate.opsForValue().get(CATALOG_AFTER);

        if (StringUtils.isEmpty(cache)) {
            //1、查询所有分类
            List<CategoryEntity> entities = categoryDao.selectList(null);

            //2、找到所有一级分类
            List<CategoryEntity> collect = entities.stream()
                    .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                    .peek(menu -> menu.setChildren(this.getChildrens(menu, entities)))
                    .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                    .collect(Collectors.toList());

            //存入缓存
            String jsonString = JSON.toJSONString(collect);
            stringRedisTemplate.opsForValue().set(CATALOG_AFTER, jsonString);

            return collect;

        } else {
            return JSON.parseObject(cache, new TypeReference<List<CategoryEntity>>() {
            });
        }
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //检查当前要删除的菜单是否被其他的地方引用

        for (Long id : asList) {
            // 1、品牌关联
            LambdaQueryWrapper<CategoryBrandRelationEntity> wrapperBrand = new LambdaQueryWrapper<CategoryBrandRelationEntity>()
                    .eq(CategoryBrandRelationEntity::getCatelogId, id);
            List<CategoryBrandRelationEntity> relationEntityList = categoryBrandRelationService.list(wrapperBrand);
            if (CollUtil.isNotEmpty(relationEntityList)) {
                throw new MallException("请先删除关联品牌信息");
            }

            // 2、属性分组关联
            LambdaQueryWrapper<AttrGroupEntity> wrapperAttrGroup = new LambdaQueryWrapper<AttrGroupEntity>()
                    .eq(AttrGroupEntity::getCatelogId, id);
            List<AttrGroupEntity> groupEntityList = attrGroupService.list(wrapperAttrGroup);
            if (CollUtil.isNotEmpty(groupEntityList)) {
                throw new MallException("请先删除关联属性分组信息");
            }

            // 3、销售属性/规格参数
            LambdaQueryWrapper<AttrEntity> wrapperAttr = new LambdaQueryWrapper<AttrEntity>()
                    .eq(AttrEntity::getCatelogId, id);
            List<AttrEntity> attrEntityList = attrService.list(wrapperAttr);
            if (CollUtil.isNotEmpty(attrEntityList)) {
                throw new MallException("请先删除关联销售属性/规格参数信息");
            }
        }

        categoryDao.deleteBatchIds(asList);

        //删除后清除缓存
        stringRedisTemplate.delete(Arrays.asList(CATALOG_BEFORE, CATALOG_AFTER));
    }

    @Override
    public Long[] finCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Override
    @CacheEvict(value = {"mall:catalog"},key = "'getLevel1Categorys'")
    public void updateCascade(CategoryEntity category) {
        //更新自己
        super.updateById(category);

        //更新关联表
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

        //删除缓存
        stringRedisTemplate.delete(Arrays.asList(CATALOG_BEFORE, CATALOG_AFTER));
    }

    @Override
    @Cacheable(value = {"mall:catalog"},key = "#root.method.name",sync = true) //sync同步会锁方法，单机锁
    public List<CategoryEntity> getLevel1Categorys() {
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryEntity::getParentCid, 0);
        return super.list(wrapper);
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        /**
         * 1、空结果缓存，解决缓存穿透
         * 2、设置过期时间加随机值，解决缓存雪崩
         * 3、加锁，解决缓存击穿
         */


        //先查缓存
        String catalogJSON = stringRedisTemplate.opsForValue().get(CATALOG_BEFORE);
        if (StringUtils.isEmpty(catalogJSON)) {

            return this.getCatalogJsonFormDbWithRedissonLock();

        } else {
            return JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
        }

    }

    private Map<String, List<Catelog2Vo>> getCatalogJsonFormDb() {
        //获取锁之后再去缓存确认，如果没有就继续查询
        String catalogJSON = stringRedisTemplate.opsForValue().get(CATALOG_BEFORE);
        if (StringUtils.isNotEmpty(catalogJSON)) {
            return JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
        }

        //查出所有1级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();

        //封装数据
        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = super.baseMapper.selectList(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, v.getCatId()));

            //封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());

                    // 找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = super.baseMapper.selectList(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, l2.getCatId()));
                    if (level3Catelog != null) {

                        List<Catelog2Vo.Catelog3Vo> catelog3Vos = level3Catelog.stream().map(l3 -> {
                            //封装成指定格式
                            return new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                        }).collect(Collectors.toList());

                        catelog2Vo.setCatalog3List(catelog3Vos);
                    }

                    return catelog2Vo;
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));

        //缓存没有查数据库并且放入
        String jsonString = JSON.toJSONString(collect);
        stringRedisTemplate.opsForValue().set(CATALOG_BEFORE, jsonString);

        return collect;
    }

    //使用redisson分布式锁
    private Map<String, List<Catelog2Vo>> getCatalogJsonFormDbWithRedissonLock() {

        RLock lock = redissonClient.getLock(LOCK + ":catalog");
        lock.lock();

        log.info("获取分布式锁成功");
        Map<String, List<Catelog2Vo>> catalogJsonFormDb;
        try {

            catalogJsonFormDb = getCatalogJsonFormDb();

        } finally {
            lock.unlock();
        }

        return catalogJsonFormDb;
    }

    //使用Java本地可重入锁synchronized
    private Map<String, List<Catelog2Vo>> getCatalogJsonFormDbWithSynLock() {

        //加锁，解决缓存击穿   (本地锁/单机锁  分布式服务锁不住)
        synchronized (this) {
            return this.getCatalogJsonFormDb();
        }
    }

    //使用redis分布式锁
    private Map<String, List<Catelog2Vo>> getCatalogJsonFormDbWithRedisLock() {

        //加锁，解决缓存击穿  分布式锁 去redis占坑
        String uuid = UUID.randomUUID().toString(); //添加唯一标识符
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(LOCK, uuid, LOCK_EXPIRE, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(lock)) {
            log.info("获取分布式锁成功:{}", uuid);
            Map<String, List<Catelog2Vo>> catalogJsonFormDb = null;
            try {
                catalogJsonFormDb = this.getCatalogJsonFormDb();
            } finally {
                //lua脚本解锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                Long ret = stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(LOCK), uuid);
            }

            //加锁成功
            //设置过期时间必须和加锁同步（原子性）
            //stringRedisTemplate.expire(LOCK, LOCK_EXPIRE, TimeUnit.SECONDS);

            //删除锁(判断是否是自己的锁)
            /*String lockValue = stringRedisTemplate.opsForValue().get(LOCK);
            if (uuid.equals(lockValue)) {
                stringRedisTemplate.delete(LOCK);
            }*/


            return catalogJsonFormDb;
        } else {
            log.warn("获取分布式锁失败：{}", uuid);
            //加锁失败...重试 自旋
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.getCatalogJsonFormDbWithRedisLock();

        }

    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;

    }


    /**
     * 递归获取子菜单
     *
     * @param root 根菜单
     * @param all  所有菜单
     * @return list
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {

        //-----------peek和map区别---------------//
        //map有返回值，peek没有返回值

        return all.stream().filter(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId())).peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity, all))).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());
    }


}
