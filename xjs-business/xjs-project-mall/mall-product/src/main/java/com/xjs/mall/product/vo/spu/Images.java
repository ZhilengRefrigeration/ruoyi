/**
  * Copyright 2019 bejson.com
  */
package com.xjs.mall.product.vo.spu;

import lombok.Data;

/**
 *图集vo
 * @author xiejs
 * @since 2022-03-20 13:50:18
 */
@Data
public class Images {
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 默认数量
     */
    private Integer defaultImg;

}
