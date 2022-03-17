package com.xjs.mall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * AttrGroupVo
 * @author xiejs
 * @since 2022-03-17
 */
@Data
public class AttrGroupResponseVo {
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    /**
     * 分类id完整路径
     */
    private List<String> catelogPath;

    /**
     * 分类名称
     */
    private String categoryName;

}
