package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.exception.MallException;
import com.xjs.mall.product.dao.AttrGroupDao;
import com.xjs.mall.product.entity.AttrAttrgroupRelationEntity;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.AttrAttrgroupRelationService;
import com.xjs.mall.product.service.AttrGroupService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.AttrGroupResponseVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
@Transactional
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        String key = (String) params.get(Query.KEY_NAME);

        LambdaQueryWrapper<AttrGroupEntity> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq(AttrGroupEntity::getAttrGroupId, key)
                        .or().like(AttrGroupEntity::getDescript, key)
                        .or().like(AttrGroupEntity::getAttrGroupName, key);
            });
        }

        if (categoryId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);

            List<AttrGroupResponseVo> responseVoList = this.setList(page.getRecords());
            PageUtils pageUtils = new PageUtils(page);
            pageUtils.setList(responseVoList);
            return pageUtils;
        } else {
            wrapper.eq(AttrGroupEntity::getCatelogId, categoryId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);

            List<AttrGroupResponseVo> responseVoList = this.setList(page.getRecords());
            PageUtils pageUtils = new PageUtils(page);
            pageUtils.setList(responseVoList);
            return pageUtils;
        }

    }

    @Override
    public void removeAttrGroup(List<Long> ids) {
        for (Long id : ids) {
            //先查询中间表是否有数据，有数据代表该数据被引用，则不能删除
            List<AttrAttrgroupRelationEntity> relationEntityList = attrAttrgroupRelationService
                    .list(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity::getAttrGroupId, id));

            if (CollUtil.isEmpty(relationEntityList)) {
                super.removeById(id);
            }else {
                throw new MallException("含有被引用的规格参数未删除，请先删除规格参数");
            }
        }
    }


    private List<AttrGroupResponseVo> setList(List<AttrGroupEntity> records) {
        return records.stream().map(attrGroupEntity -> {
            AttrGroupResponseVo attrGroupResponseVo = new AttrGroupResponseVo();
            BeanUtils.copyProperties(attrGroupEntity, attrGroupResponseVo);
            //获取分类实体类，主要为了获取分类名称给vo
            CategoryEntity categoryEntity = categoryService.getById(attrGroupEntity.getCatelogId());
            attrGroupResponseVo.setCategoryName(categoryEntity.getName());
            return attrGroupResponseVo;
        }).collect(Collectors.toList());
    }


}
