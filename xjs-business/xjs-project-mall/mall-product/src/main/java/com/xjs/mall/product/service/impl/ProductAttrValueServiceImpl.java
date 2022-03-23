package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.ProductAttrValueDao;
import com.xjs.mall.product.entity.AttrEntity;
import com.xjs.mall.product.entity.ProductAttrValueEntity;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.service.ProductAttrValueService;
import com.xjs.mall.product.vo.spu.BaseAttrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Autowired
    private AttrService attrService;

    @Override
    public void saveProductAttr(List<BaseAttrs> baseAttrs,Long spuId) {
        List<ProductAttrValueEntity> productAttrValueEntityList = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setAttrId(attr.getAttrId());

            //查询属性名
            AttrEntity attrEntity = attrService.getById(attr.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());

            productAttrValueEntity.setAttrValue(attr.getAttrValues());
            productAttrValueEntity.setQuickShow(attr.getShowDesc());
            productAttrValueEntity.setSpuId(spuId);
            return productAttrValueEntity;
        }).collect(Collectors.toList());

        super.saveBatch(productAttrValueEntityList);

    }



}
