package com.xjs.mall.ware.vo;

import lombok.Data;

/**
 * 采购单采购项目vo
 */
@Data
public class PurchaseItemDoneVo {
    //{itemId:1,status:4,reason:""}
    private Long itemId;
    private Integer status;
    private String reason;
}
