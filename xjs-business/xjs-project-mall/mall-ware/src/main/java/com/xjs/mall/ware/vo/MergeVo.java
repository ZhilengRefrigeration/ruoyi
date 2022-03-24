package com.xjs.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * 合并采购单vo
 */
@Data
public class MergeVo {

   private Long purchaseId; //整单id
   private List<Long> items;//[1,2,3,4] //合并项集合
}
