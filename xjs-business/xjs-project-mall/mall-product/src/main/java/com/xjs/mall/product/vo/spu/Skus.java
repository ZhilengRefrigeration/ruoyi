/**
 * Copyright 2019 bejson.com
 */
package com.xjs.mall.product.vo.spu;

import com.xjs.mall.to.MemberPrice;
import com.xjs.validation.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 *sku vo
 * @author xiejs
 * @since 2022-03-20 13:48:50
 */
@Data
public class Skus {

    /**
     * 属性bean
     */
    private List<Attr> attr;
    /**
     * sku名称
     */
    @NotBlank(message = "sku名称不能为空", groups = {AddGroup.class})
    private String skuName;
    /**
     * 价格
     */
    @NotNull(message = "价格不能为空", groups = {AddGroup.class})
    @Min(message = "价格应该大于 0 ", groups = {AddGroup.class},value = 1)
    private BigDecimal price;
    /**
     * sku标题
     */
    @NotBlank(message = "sku标题不能为空", groups = {AddGroup.class})
    private String skuTitle;
    /**
     * sku副标题
     */
    @NotBlank(message = "sku副标题不能为空", groups = {AddGroup.class})
    private String skuSubtitle;
    /**
     * 图集
     */
    private List<Images> images;
    /**
     * 图集描述
     */
    private List<String> descar;
    /**
     * 满多少满减
     */
    private Integer fullCount;
    /**
     * 满减数量
     */
    private BigDecimal discount;

    private Integer countStatus;
    /**
     * 正常价格
     */
    private BigDecimal fullPrice;
    /**
     * 优惠价格
     */
    private BigDecimal reducePrice;
    /**
     * 价格状态
     */
    private Integer priceStatus;

    /**
     * 会员bean
     */
    private List<MemberPrice> memberPrice;


}
