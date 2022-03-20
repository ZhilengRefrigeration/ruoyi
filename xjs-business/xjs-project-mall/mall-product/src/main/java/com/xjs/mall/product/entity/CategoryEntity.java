package com.xjs.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotBlank(message = "分类名称不能为空", groups = {UpdateGroup.class, AddGroup.class})
	@Size(max = 50, message = "请控制分类名称长度在50字符", groups = {UpdateGroup.class,AddGroup.class})
	private String name;
	/**
	 * 父分类id
	 */
	private Long parentCid;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@TableLogic(value = "1",delval = "0")
	private Integer showStatus;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标地址
	 */
	@Size(max = 50, message = "请控制图标地址长度在50字符", groups = {UpdateGroup.class,AddGroup.class})
	private String icon;
	/**
	 * 计量单位
	 */
	@Size(max = 50, message = "请控制计量单位长度在50字符", groups = {UpdateGroup.class,AddGroup.class})
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

	/**
	 * 子分类
	 */
	@TableField(exist = false)
	private List<CategoryEntity> children;

}
