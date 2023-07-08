package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by jackma on 2020/7/20.
 */
@ApiModel(value="用户登录请求信息")
public class WxLoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号(手机号/微信code)
     */
    @Setter
    @Getter
    @ApiModelProperty(value="账号(手机号/微信code)",required=false)
    @NotBlank(message = "登录账号不能为空")
    private String loginName;
    /**
     * 登录密码
     */
    @Setter
    @Getter
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 用户手机号（微信登录需要传）
     */
    @Setter
    @Getter
    @ApiModelProperty(value = "用户手机号")
    private String telephone;
    /**
     *头像（微信登录需要传）
     */
    @Setter
    @Getter
    private String avatar;
    /**
     *性别（微信登录需要传）
     */
    @Setter
    @Getter
    private Integer gender;
    /**
     *昵称（微信登录需要传）
     */
    @Setter
    @Getter
    private String nickname;
    /**
     * 登录类型(1:pc;2:wx)
     */
    @Setter
    @Getter
    @ApiModelProperty(value = "登录类型(1:pc;2:wx)")
    @NotBlank(message = "登录类型不能为空")
    private String type;
}
