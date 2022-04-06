package com.xjs.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.mall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    /**
     * 添加库存
     * @param skuId 商品id
     * @param wareId 仓库id
     * @param skuNum 商品数量
     */
    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 获取库存
     * @param skuId sku id
     * @return 库存数
     */
    Long getSkuStock(@Param("skuId") Long skuId);
}
