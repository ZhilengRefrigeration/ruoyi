package com.xjs.mall.search.vo;

import com.xjs.mall.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * 检索响应信息实体
 *
 * @author xiejs
 * @since 2022-05-11
 */
@Data
public class SearchResult {

    /**
     * 查询到的所有商品信息
     */
    private List<SkuEsModel> products;

    /**
     * 当前页面
     */
    private Integer pageNum;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页码
     */
    private Integer totalPages;

    /**
     * 当前结果查询的所有品牌
     */
    private List<BrandVo> brands;

    /**
     * 当前结果查询的所有属性
     */
    private List<AttrVo> attrs;

    /**
     * 当前结果查询的所有分类
     */
    private List<CatalogVo> catalogs;

    private List<Integer> pageNavs;

    //================以上是返回给页面的所有信息=========================

    /* 面包屑导航数据 */
    private List<NavVo> navs;

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }


    @Data
    public static class BrandVo {
        /**
         * 品牌id
         */
        private Long brandId;

        /**
         * 品牌名
         */
        private String brandName;

        /**
         * 品牌logo
         */
        private String brandImg;
    }

    @Data
    public static class AttrVo {

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
        private List<String> attrValue;
    }

    @Data
    public static class CatalogVo {

        /**
         * 分类ID
         */
        private Long catalogId;

        /**
         * 分类名称
         */
        private String catalogName;

    }


}
