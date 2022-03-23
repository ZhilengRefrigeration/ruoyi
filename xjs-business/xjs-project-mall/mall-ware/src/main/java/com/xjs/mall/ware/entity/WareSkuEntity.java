package com.xjs.mall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品库存
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
@Data
@TableName("wms_ware_sku")
public class WareSkuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * sku_id
     */
    @NotNull(message = "sku_id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long skuId;
    /**
     * 仓库id
     */
    @NotNull(message = "仓库id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long wareId;
    /**
     * 库存数
     */
    @NotNull(message = "库存数不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0,message = "库存不能小于零",groups = {AddGroup.class,UpdateGroup.class})
    @Max(value = 9999999,message = "库存不能大于9999999",groups = {AddGroup.class,UpdateGroup.class})
    private Integer stock;
    /**
     * sku_name
     */
    @NotBlank(message = "sku_name不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String skuName;
    /**
     * 锁定库存
     */
    @NotNull(message = "锁定库存不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0,message = "锁定库存不能小于零",groups = {AddGroup.class,UpdateGroup.class})
    @Max(value = 9999999,message = "锁定库存不能大于9999999",groups = {AddGroup.class,UpdateGroup.class})
    private Integer stockLocked;

}
