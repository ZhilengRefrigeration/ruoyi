package com.xjs.mall.ware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 商品库存vo
 * @author xiejs
 * @since 2022-03-23
 */
@Data
public class WareSkuVo {
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * 仓库id
     */
    private Long wareId;
    /**
     * 库存数
     */
    private Integer stock;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 锁定库存
     */
    private Integer stockLocked;

    /**
     * 仓库名称
     */
    private String wareName;
}
