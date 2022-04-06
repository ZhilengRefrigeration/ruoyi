package com.xjs.mall.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * spu es 模型
 * @author xiejs
 * @since 2022-04-06
 */
@Data
public class SkuEsModel {

    /**
     * 商品sku id
     */
    private Long skuId;

    /**
     * 商品spu id
     */
    private Long spuId;

    /**
     * sku标题
     */
    private String skuTitle;

    /**
     * sku价格
     */
    private BigDecimal skuPrice;

    /**
     * sku默认图片
     */
    private String skuImg;

    /**
     * 销量
     */
    private Long saleCount;

    /**
     * 是否拥有库存
     */
    private Boolean hasStock;

    /**
     * 热度评分
     */
    private Long hotScore;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long catalogId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandImg;

    /**
     * 分类名
     */
    private String catalogName;


    private List<Attrs> attrs;


    @Data
    public static class Attrs{
        /**
         * 属性id
         */
        private Long attrId;

        /**
         * 属性名
         */
        private String attrName;

        /**
         * 属性值
         */
        private String attrValue;
    }


}
