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
	 *
	 */
	private Long assigneeId;
	/**
	 *
	 */
	private String assigneeName;
	/**
	 *
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
	 *
	 */
	private Integer status;
	/**
	 *
	 */
	private Long wareId;
	/**
	 *
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
