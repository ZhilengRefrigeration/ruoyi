package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.AttrAttrgroupRelationDao;
import com.xjs.mall.product.entity.AttrAttrgroupRelationEntity;
import com.xjs.mall.product.service.AttrAttrgroupRelationService;
import com.xjs.mall.product.vo.AttrGroupRelationVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {


    @Override
    public void saveBatch(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrId(item.getAttrId());
            relationEntity.setAttrGroupId(item.getAttrGroupId());
            return relationEntity;
        }).collect(Collectors.toList());

        super.saveBatch(collect);
    }
}
