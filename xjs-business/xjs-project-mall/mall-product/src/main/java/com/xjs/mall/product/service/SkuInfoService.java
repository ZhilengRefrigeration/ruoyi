package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.vo.sku.SkuItemVo;
import com.xjs.utils.PageUtils;
import com.xjs.mall.product.entity.SkuInfoEntity;

import java.util.List;
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

    /**
     * 根据spu获取skus
     * @param spuId id
     * @return list
     */
    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    /**
     * 查询商品详情
     * @param skuId 商品id
     * @return 商品详情vo
     */
    SkuItemVo item(Long skuId);
}

