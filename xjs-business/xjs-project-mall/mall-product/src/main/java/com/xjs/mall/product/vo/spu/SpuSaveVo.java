/**
 * Copyright 2019 bejson.com
 */
package com.xjs.mall.product.vo.spu;

import com.xjs.validation.group.AddGroup;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Spu保存的vo
 * @author xiejs
 * @since 2022-03-20 13:42:13
 */
@Data
public class SpuSaveVo {


    /**
     * spu名称
     */
    @NotBlank(message = "商品名称不能为空",groups = {AddGroup.class})
    @Size(message = "商品名称长度在 1 到 100 之间",groups = {AddGroup.class},max = 100)
    private String spuName;
    /**
     * spu描述
     */
    @NotBlank(message = "商品描述不能为空",groups = {AddGroup.class})
    @Size(message = "商品描述长度在 1 到 200 之间",groups = {AddGroup.class},max = 200)
    private String spuDescription;
    /**
     * 三级分类id
     */
    @NotNull(message = "三级分类不能为空",groups = {AddGroup.class})
    private Long catalogId;
    /**
     * 品牌id
     */
    @NotNull(message = "品牌不能为空",groups = {AddGroup.class})
    private Long brandId;
    /**
     * 重量
     */
    @NotNull(message = "商品重量不能为空",groups = {AddGroup.class})
    private BigDecimal weight;
    /**
     * 发布状态
     */
    private Integer publishStatus;
    /**
     * 图集描述
     */
    private List<String> decript;
    /**
     * 图集
     */
    private List<String> images;

    @Valid
    private Bounds bounds;
    /**
     * 基本属性
     */
    @Valid
    private List<BaseAttrs> baseAttrs;
    /**
     * sku
     */
    @Valid
    private List<Skus> skus;



}
