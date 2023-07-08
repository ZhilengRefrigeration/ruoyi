package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by jackma on 2020/7/20.
 */
@ApiModel(value="用户注册请求信息")
public class WxRegisterRequest {
    @Setter
    @Getter
    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String account;

    @Setter
    @Getter
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Setter
    @Getter
    @ApiModelProperty(value = "电话")
    private String telephone;

    @Setter
    @Getter
    @ApiModelProperty(value = "真实名称")
    private String realName;

    @Setter
    @Getter
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Setter
    @Getter
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Setter
    @Getter
    @ApiModelProperty(value = "性别(1.男 2.女) ")
    private Integer gender;
}
