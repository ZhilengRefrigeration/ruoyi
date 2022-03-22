package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {


    /**
     * 保存sku信息
     * @param skuInfoEntity sku实体类
     */
    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    /**
     * 根据条件检索列表
     * @param params 条件
     * @return page
     */
    PageUtils queryPageByCondition(Map<String, Object> params);
}
