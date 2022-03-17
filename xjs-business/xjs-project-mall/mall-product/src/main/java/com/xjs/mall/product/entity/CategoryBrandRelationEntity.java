package com.xjs.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 品牌分类关联
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	private Long brandId;
	/**
	 * 分类id
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	private Long catelogId;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 *分类名称
	 */
	private String catelogName;

}
