package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.SkuImagesEntity;

import java.util.List;

/**
 * sku图片
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    /**
     * 按 Sku Id 获取图片
     * @param skuId skuid
     * @return image实体
     */
    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

