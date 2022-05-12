package com.xjs.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.mall.product.entity.SkuSaleAttrValueEntity;
import com.xjs.mall.product.vo.sku.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

    /**
     * 通过 Spu Id 获取销售属性
     * @param spuId spuId
     * @return list
     */
    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(@Param("spuId") Long spuId);
}
