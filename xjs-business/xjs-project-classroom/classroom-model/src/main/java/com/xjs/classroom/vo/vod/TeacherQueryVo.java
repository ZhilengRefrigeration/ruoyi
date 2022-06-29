package com.xjs.classroom.vo.vod;

import com.xjs.validation.group.SelectGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TeacherQueryVo {

	@ApiModelProperty(value = "讲师姓名")
	@Size(max = 10, message = "请控制讲师名称长度在10字符", groups = {SelectGroup.class})
	private String name;

	@ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
	private Integer level;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateBegin;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateEnd;


}

