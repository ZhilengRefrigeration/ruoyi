package com.xjs.classroom.model.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjs.classroom.model.base.BaseEntity;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ApiModel(description = "Teacher")
@TableName("teacher")
public class Teacher extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "讲师名称")
	@TableField("name")
	@NotBlank(message = "讲师名称不能为空", groups = {UpdateGroup.class,AddGroup.class})
	@Size(max = 10, message = "请控制讲师名称长度在10字符", groups = {UpdateGroup.class, AddGroup.class})
	private String name;

	@ApiModelProperty(value = "讲师简介")
	@TableField("intro")
	@Size(max = 50, message = "请控制讲师简介长度在10字符", groups = {UpdateGroup.class,AddGroup.class})
	private String intro;

	@ApiModelProperty(value = "讲师资历,一句话说明讲师")
	@Size(max = 500, message = "请控制讲师资历长度在500字符", groups = {UpdateGroup.class,AddGroup.class})
	@TableField("career")
	private String career;

	@ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
	@TableField("level")
	@NotNull(message = "讲师名称不能为空", groups = {UpdateGroup.class,AddGroup.class})
	private Integer level;

	@ApiModelProperty(value = "讲师头像")
	@TableField("avatar")
	@NotBlank(message = "讲师头像不能为空", groups = {UpdateGroup.class,AddGroup.class})
	private String avatar;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	@NotNull(message = "排序不能为空", groups = {UpdateGroup.class,AddGroup.class})
	private Integer sort;

	@ApiModelProperty(value = "入驻时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("join_date")
	@NotNull(message = "入驻时间不能为空", groups = {UpdateGroup.class,AddGroup.class})
	private LocalDate joinDate;

}
