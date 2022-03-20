package com.xjs.mall.product.vo.spu;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息展示vo
 * @author xiejs
 * @since 2022-03-21
 */
@Data
public class SpuInfoVo {
    private Long id;
    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品描述
     */
    private String spuDescription;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     *
     */
    private BigDecimal weight;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    /**
     * 分类名称
     */
    private String catalogName;

    /**
     * 品牌名称
     */
    private String brandName;
}
