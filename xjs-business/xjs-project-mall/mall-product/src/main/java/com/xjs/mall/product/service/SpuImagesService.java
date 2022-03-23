package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.SpuImagesEntity;

import java.util.List;

/**
 * spu图片
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    /**
     * 保存spu图片信息
     * @param id spuId
     * @param images 图片集合
     */
    void saveImages(Long id, List<String> images);
}

