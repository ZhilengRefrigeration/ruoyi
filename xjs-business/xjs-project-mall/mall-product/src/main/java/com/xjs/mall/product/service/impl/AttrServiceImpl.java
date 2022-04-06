package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.product.dao.AttrAttrgroupRelationDao;
import com.xjs.mall.product.dao.AttrDao;
import com.xjs.mall.product.dao.AttrGroupDao;
import com.xjs.mall.product.dao.CategoryDao;
import com.xjs.mall.product.entity.AttrAttrgroupRelationEntity;
import com.xjs.mall.product.entity.AttrEntity;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.AttrAttrgroupRelationService;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.AttrGroupRelationVo;
import com.xjs.mall.product.vo.AttrResponseVo;
import com.xjs.mall.product.vo.AttrVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xjs.consts.ProductConstant.AttrEnum.ATTR_TYPE_BASE;
import static com.xjs.consts.ProductConstant.AttrEnum.ATTR_TYPE_SALE;


@Service("attrService")
@Transactional
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Resource
    private AttrGroupDao attrGroupDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        //保存基本数据
        super.save(attrEntity);

        //保存关联关系
        if (attr.getAttrType() == ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId()!=null) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType) {
        LambdaQueryWrapper<AttrEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttrEntity::getAttrType, "base".equalsIgnoreCase(attrType) ? ATTR_TYPE_BASE.getCode() : ATTR_TYPE_SALE.getCode());

        if (catelogId != 0) {
            wrapper.eq(AttrEntity::getCatelogId, catelogId);
        }

        String key = (String) params.get(Query.KEY_NAME);
        wrapper.and(StringUtils.isNotEmpty(key), obj -> {
            obj.like(AttrEntity::getAttrName, key);
        });

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrResponseVo> collect = records.stream().map(attrEntity -> {
            AttrResponseVo attrResponseVo = new AttrResponseVo();
            BeanUtils.copyProperties(attrEntity, attrResponseVo);

            //1、设置分类和分组的数据
            if ("base".equalsIgnoreCase(attrType)) {
                AttrAttrgroupRelationEntity attrId = attrAttrgroupRelationDao.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq(AttrAttrgroupRelationEntity::getAttrId, attrEntity.getAttrId()));

                if (attrId != null && attrId.getAttrGroupId()!=null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrResponseVo.setCatelogName(categoryEntity.getName());
            }

            return attrResponseVo;

        }).collect(Collectors.toList());

        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public AttrResponseVo getAttrInfo(Long attrId) {
        AttrEntity attrEntity = super.getById(attrId);
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        BeanUtils.copyProperties(attrEntity, attrResponseVo);

        if (attrEntity.getAttrType() == ATTR_TYPE_BASE.getCode()) {
            //设置分组信息
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                    new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrId, attrId)
            );
            if (attrAttrgroupRelationEntity != null) {
                attrResponseVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());

                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());

                if (attrGroupEntity != null) {
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        //设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.finCatelogPath(catelogId);
        List<String> collect = Arrays.stream(catelogPath).map(String::valueOf).collect(Collectors.toList());
        attrResponseVo.setCatelogPath(collect);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if (categoryEntity != null) {
            attrResponseVo.setCatelogName(categoryEntity.getName());
        }

        return attrResponseVo;
    }

    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        if (attrEntity.getAttrType() == ATTR_TYPE_BASE.getCode()) {
            //1、修改分组关联
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();

            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());

            Long count = attrAttrgroupRelationDao.selectCount(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId()));
            if (count > 0) {
                attrAttrgroupRelationDao.update(relationEntity,
                        new LambdaUpdateWrapper<AttrAttrgroupRelationEntity>()
                                .eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId()));

            } else {
                attrAttrgroupRelationDao.insert(relationEntity);
            }
        }

    }

    @Override
    public void removeAttr(List<Long> asList) {
        //删除自身
        super.removeByIds(asList);
        //级联删除中间表数据
        for (Long id : asList) {
            attrAttrgroupRelationService.remove(new LambdaUpdateWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity::getAttrId, id));
        }
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        LambdaQueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                .eq(AttrAttrgroupRelationEntity::getAttrGroupId, attrgroupId);
        List<AttrAttrgroupRelationEntity> entityList = attrAttrgroupRelationDao.selectList(queryWrapper);

        List<Long> collect = entityList.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

        List<AttrEntity> attrEntities = new ArrayList<>();
        if (CollUtil.isNotEmpty(collect)) {
            attrEntities = super.listByIds(collect);
        }
        return attrEntities;
    }

    @Override
    public void deleteRelation(List<AttrGroupRelationVo> vos) {
        LambdaQueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new LambdaQueryWrapper<>();

        //循环拼接条件
        for (int i = 0; i < vos.size(); i++) {
            int finalI = i;
            queryWrapper.eq(AttrAttrgroupRelationEntity::getAttrId, vos.get(finalI).getAttrId()).and(obj -> {
                obj.eq(AttrAttrgroupRelationEntity::getAttrGroupId, vos.get(finalI).getAttrGroupId());
            }).or(i != vos.size() - 1);
        }

        attrAttrgroupRelationService.remove(queryWrapper);
    }

    @Override
    public PageUtils getAttrNoRelation(Map<String, Object> params, Long attrgroupId) {
        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();
        //2、当前分组只能关联别的分组没有引用的属性
        //2.1)、当前分类下的其他分组
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> collect = group.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());

        //2.2)、这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

        //2.3)、从当前分类的所有属性中移除这些属性；
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type", ATTR_TYPE_BASE.getCode());
        if (CollUtil.isNotEmpty(attrIds)) {
            wrapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        wrapper.and(StringUtils.isNotEmpty(key), (w) -> {
            w.like("value_select", key).or().like("attr_name", key);
        });
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {
        return super.baseMapper.selectSearchAttrIds(attrIds);
    }

}
