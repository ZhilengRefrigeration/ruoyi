package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.ProductAttrValueDao;
import com.xjs.mall.product.entity.AttrEntity;
import com.xjs.mall.product.entity.ProductAttrValueEntity;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.service.ProductAttrValueService;
import com.xjs.mall.product.vo.spu.BaseAttrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("productAttrValueService")
@Transactional
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

    @Override
    public List<ProductAttrValueEntity> baseAtteListForSpu(Long spuId) {
        LambdaQueryWrapper<ProductAttrValueEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductAttrValueEntity::getSpuId, spuId);
        return super.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {
        //删除这个spuId之前对应的所有属性
        super.baseMapper.delete(new LambdaQueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity::getSpuId, spuId));

        //更新
        List<ProductAttrValueEntity> collect = entities.stream().peek(item -> item.setSpuId(spuId)).collect(Collectors.toList());

        super.saveBatch(collect);
    }


}
