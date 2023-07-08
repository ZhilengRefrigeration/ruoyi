package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 个人荣誉榜
 */
@ApiModel(value = "个人荣誉榜")
@Setter
@Getter
public class PersonalHonorResponse {

    @ApiModelProperty(value = "荣誉名称", required = false)
    private String honorName;

    @ApiModelProperty(value = "荣誉人员", required = false)
    private String honoraryPersonnel;

    @ApiModelProperty(value = "球队名称", required = false)
    private String teamName;

}
