/**
  * Copyright 2019 bejson.com
  */
package com.xjs.mall.product.vo.spu;

import lombok.Data;

/**
 *属性vo
 * @author xiejs
 * @since 2022-03-20 13:49:29
 */
@Data
public class Attr {

    /**
     * 属性Id
     */
    private Long attrId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValue;

}
