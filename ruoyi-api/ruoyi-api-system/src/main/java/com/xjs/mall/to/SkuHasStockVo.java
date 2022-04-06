package com.xjs.mall.to;

import lombok.Data;

/**
 * sku库存查询vo
 * @author xiejs
 * @since 2022-04-06
 */
@Data
public class SkuHasStockVo {
    /**
     * sku id
     */
    private Long skuId;

    /**
     * 是否有库存
     */
    private Boolean hasStock;
}
