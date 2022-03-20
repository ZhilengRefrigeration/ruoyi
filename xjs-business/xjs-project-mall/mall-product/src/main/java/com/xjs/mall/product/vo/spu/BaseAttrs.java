
package com.xjs.mall.product.vo.spu;

import lombok.Data;


/**
 * 基本属性vo
 */
@Data
public class BaseAttrs {

    /**
     * 属性Id
     */
    private Long attrId;
    /**
     * 属性值
     */
    private String attrValues;
    /**
     * 属性描述状态
     */
    private Integer showDesc;

}
