package com.xjs.mall.product.vo.sku;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Sku 项目销售 Attr Vo
 * @author xiejs
 * @since 2022-05-12
 */
@Data
@ToString
public class SkuItemSaleAttrVo {
    private Long attrId;

    private String attrName;

    private List<AttrValueWithSkuIdVO> attrValues;
}
