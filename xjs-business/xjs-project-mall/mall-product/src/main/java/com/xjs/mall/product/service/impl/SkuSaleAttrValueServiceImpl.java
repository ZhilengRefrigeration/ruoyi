package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.SkuSaleAttrValueDao;
import com.xjs.mall.product.entity.SkuSaleAttrValueEntity;
import com.xjs.mall.product.service.SkuSaleAttrValueService;
import com.xjs.mall.product.vo.sku.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {


    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId) {
        SkuSaleAttrValueDao baseMapper = super.baseMapper;

        return baseMapper.getSaleAttrsBySpuId(spuId);
    }
}
