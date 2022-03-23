package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.SpuInfoDescEntity;

/**
 * spu信息介绍
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    /**
     * 保存spu描述信息
     * @param descEntity spu描述实体类
     */
    void saveSpuInfoDesc(SpuInfoDescEntity descEntity);
}

