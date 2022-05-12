package com.xjs.mall.product.vo.sku;

import com.xjs.mall.product.vo.spu.Attr;
import lombok.Data;

import java.util.List;

/**
 * Spu 项目属性组 Vo
 * @author xiejs
 * @since 2022-05-12
 */
@Data
public class SpuItemAttrGroupVo {
    private String groupName;

    private List<Attr> attrs;
}
