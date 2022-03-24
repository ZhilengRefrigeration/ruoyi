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
import java.util.Date;

/**
 * 采购信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
@Data
@TableName("wms_purchase")
public class PurchaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;

	/**
	 *用户id
	 */
	private Long assigneeId;
	/**
	 *用户名
	 */
	private String assigneeName;
	/**
	 *手机号
	 */
	private String phone;

	/**
	 * 优先级
	 */
	@NotNull(message = "优先级不能为空",groups = {AddGroup.class, UpdateGroup.class})
	@Min(value = 0,message = "优先级最小为0",groups = {AddGroup.class, UpdateGroup.class})
	@Max(value = 9999,message = "优先级最大为9999",groups = {AddGroup.class, UpdateGroup.class})
	private Integer priority;
	/**
	 *采购单状态
	 */
	private Integer status;
	/**
	 *仓库id
	 */
	private Long wareId;
	/**
	 *总金额
	 */
	private BigDecimal amount;
	/**
	 *
	 */
	private Date createTime;
	/**
	 *
	 */
	private Date updateTime;

}
