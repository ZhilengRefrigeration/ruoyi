package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.SkuImagesDao;
import com.xjs.mall.product.entity.SkuImagesEntity;
import com.xjs.mall.product.service.SkuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {


    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        SkuImagesDao skuImagesDao = super.baseMapper;

        return skuImagesDao.selectList(new LambdaQueryWrapper<SkuImagesEntity>()
                .eq(SkuImagesEntity::getSkuId, skuId));

    }
}
