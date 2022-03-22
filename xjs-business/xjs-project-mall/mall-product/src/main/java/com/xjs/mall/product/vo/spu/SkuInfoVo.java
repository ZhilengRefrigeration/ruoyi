package com.xjs.mall.product.vo.spu;

import lombok.Data;

import java.math.BigDecimal;

/**
 * sku信息展示vo
 * @author xiejs
 * @since 2022-03-21
 */
@Data
public class SkuInfoVo {
    private Long skuId;
    /**
     * spuId
     */
    private Long spuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku介绍描述
     */
    private String skuDesc;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 默认图片
     */
    private String skuDefaultImg;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;

    /**
     * 分类名称
     */
    private String catalogName;

    /**
     * spu名
     */
    private String spuName;

    /**
     * 品牌名称
     */
    private String brandName;
}
