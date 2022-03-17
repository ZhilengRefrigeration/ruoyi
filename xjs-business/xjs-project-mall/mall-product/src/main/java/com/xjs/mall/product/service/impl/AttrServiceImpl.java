package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.consts.ProductConstant;
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
import com.xjs.mall.product.vo.AttrResponseVo;
import com.xjs.mall.product.vo.AttrVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId) {
        LambdaQueryWrapper<AttrEntity> wrapper = new LambdaQueryWrapper<>();

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
            AttrAttrgroupRelationEntity attrId = attrAttrgroupRelationDao.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity::getAttrId, attrEntity.getAttrId()));

            if (attrId != null) {
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
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

        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
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

}
