package com.xjs.mall.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Attr响应Vo
 * @author xiejs
 * @since 2022-03-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttrResponseVo extends AttrVo {

    /**
     * 组名称
     */
    private String groupName;


    /**
     * 分类名称
     */
    private String catelogName;


    /**
     * catelog路径
     */
    private List<String> catelogPath;




}
