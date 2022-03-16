package com.xjs.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空", groups = {UpdateGroup.class, AddGroup.class})
	@Size(max = 50, message = "请控制品牌名长度在50字符", groups = {UpdateGroup.class, SelectGroup.class,AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "品牌logo地址不能为空", groups = {UpdateGroup.class, AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	@NotBlank(message = "介绍不能为空", groups = {UpdateGroup.class, AddGroup.class})
	@Size(max = 500, message = "请控制介绍长度在500字符", groups = {UpdateGroup.class, SelectGroup.class,AddGroup.class})
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索首字母不能为空", groups = {UpdateGroup.class, AddGroup.class})
	@Size(max = 1, message = "请控制检索首字母在1字符", groups = {UpdateGroup.class, SelectGroup.class,AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {UpdateGroup.class, AddGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空",groups = {UpdateGroup.class, AddGroup.class})
	@Min(value = 0,message = "排序必须大于等于0",groups = {UpdateGroup.class, AddGroup.class})
	private Integer sort;

}
