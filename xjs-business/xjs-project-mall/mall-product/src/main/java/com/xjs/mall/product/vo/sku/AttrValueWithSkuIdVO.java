package com.xjs.mall.product.vo.sku;

import lombok.Data;
import lombok.ToString;

/**
 * 带有 Sku Id VO 的 Attr 值
 * @author xiejs
 * @since 2022-05-12
 */
@Data
@ToString
public class AttrValueWithSkuIdVO {
    private String attrValue;

    private String skuIds;
}
