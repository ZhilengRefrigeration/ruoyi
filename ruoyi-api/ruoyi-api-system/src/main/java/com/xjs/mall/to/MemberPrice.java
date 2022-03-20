
package com.xjs.mall.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员价vo
 * @author xiejs
 * @since 2022-03-20 13:51:55
 */
@Data
public class MemberPrice {

    private Long id;
    /**
     * 会员名称
     */
    private String name;
    /**
     * 会员价格
     */
    private BigDecimal price;

}
