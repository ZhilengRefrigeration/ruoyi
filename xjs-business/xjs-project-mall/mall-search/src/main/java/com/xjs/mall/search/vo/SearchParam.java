package com.xjs.mall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * es 检索条件实体
 *
 * @author xiejs
 * @since 2022-05-11
 */
@Data
public class SearchParam {

    /**
     * 页面传递的全文匹配关键字
     */
    private String keyword;

    /**
     * 三级分类id
     */
    private Long catalog3Id;

    /**
     * 排序条件 saleCount_asc/desc   skuPrice_asc/desc   hotScore_asc/desc
     */
    private String sort;


    /**
     * 过滤条件
     * hasStock(是否有货) 、skuPrice区间 、 brandId 、 catalog3Id 、 attrs
     */


    /**
     * 是否只显示有货(0:无库存 1:有库存)
     */
    private Integer hasStock = 1;

    /**
     * 价格区间查询
     */
    private String skuPrice;

    /**
     * 品牌id
     */
    private List<Long> brandId;

    /**
     * 按照属性进行筛选
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum = 1;


}
