package com.xjs.mall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
@Data
@TableName("wms_purchase_detail")
public class PurchaseDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 采购单id
	 */
	private Long purchaseId;
	/**
	 * 采购商品id
	 */
	@NotNull(message = "采购商品id不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Long skuId;
	/**
	 * 采购数量
	 */
	@NotNull(message = "采购数量不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Max(value = 999999,message = "采购最大数999999",groups = {AddGroup.class, UpdateGroup.class})
	@Min(value = 1,message = "采购最小数1",groups = {AddGroup.class, UpdateGroup.class})
	private Integer skuNum;
	/**
	 * 采购金额
	 */
	private BigDecimal skuPrice;
	/**
	 * 仓库id
	 */
	@NotNull(message = "仓库id不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Long wareId;
	/**
	 * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
	 */
	private Integer status;

}
