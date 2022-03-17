package com.xjs.mall.product.vo;

import com.xjs.validation.annotation.CheckNumber;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 商品属性Vo
 *
 * @author xiejs
 * @since 2022-03-17
 */
@Data
public class AttrVo {
    private Long attrId;
    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Size(max = 30, groups = {AddGroup.class, UpdateGroup.class}, message = "属性名长度在 1 到 30 字符")
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @NotNull(message = "是否需要检索不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @CheckNumber(num = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer searchType;
    /**
     * 属性图标
     */
    @NotBlank(message = "属性图标不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Size(max = 30, groups = {AddGroup.class, UpdateGroup.class}, message = "属性图标长度在 1 到 30 字符")
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    @Size(max = 255, groups = {AddGroup.class, UpdateGroup.class}, message = "可选值列表长度在 1 到 255 字符")
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @CheckNumber(num = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(message = "属性类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer attrType;

    /**
     * 值类型（多选单选）
     */
    @NotNull(message = "值类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer valueType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @CheckNumber(num = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(message = "启用状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long enable;
    /**
     * 所属分类
     */
    @NotNull(message = "所属分类id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @CheckNumber(num = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(message = "快速展示不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer showDesc;


    private Long attrGroupId;

}
