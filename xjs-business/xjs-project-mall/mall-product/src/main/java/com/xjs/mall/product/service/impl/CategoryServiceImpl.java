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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.xjs.consts.RedisConst.CATALOG_AFTER;
import static com.xjs.consts.RedisConst.CATALOG_BEFORE;


@Service("categoryService")
@Transactional
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
        stringRedisTemplate.delete(Arrays.asList(CATALOG_BEFORE,CATALOG_AFTER));
    }

    @Override
    public Long[] finCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Override
    public void updateCascade(CategoryEntity category) {
        //更新自己
        super.updateById(category);

        //更新关联表
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

        //删除缓存
        stringRedisTemplate.delete(Arrays.asList(CATALOG_BEFORE,CATALOG_AFTER));
    }

    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryEntity::getParentCid, 0);
        return super.list(wrapper);
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        //先查缓存
        String catalogJSON = stringRedisTemplate.opsForValue().get(CATALOG_BEFORE);
        if (StringUtils.isEmpty(catalogJSON)) {
            //缓存没有查数据库并且放入
            Map<String, List<Catelog2Vo>> catalogJsonFormDb = this.getCatalogJsonFormDb();
            String jsonString = JSON.toJSONString(catalogJsonFormDb);
            stringRedisTemplate.opsForValue().set(CATALOG_BEFORE, jsonString);
            return catalogJsonFormDb;
        } else {
            return JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
        }

    }

    //可优化
    private Map<String, List<Catelog2Vo>> getCatalogJsonFormDb() {
        //查出所有1级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();

        //封装数据
        return level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
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
