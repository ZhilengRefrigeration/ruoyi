package com.xjs.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * spu属性值
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
@Data
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 商品id
	 */
	private Long spuId;
	/**
	 * 属性id
	 */
	private Long attrId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 属性值
	 */
	@Size(message = "属性值应小于 20 字符",groups = UpdateGroup.class,max = 20)
	private String attrValue;
	/**
	 * 顺序
	 */
	private Integer attrSort;
	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	private Integer quickShow;

}
