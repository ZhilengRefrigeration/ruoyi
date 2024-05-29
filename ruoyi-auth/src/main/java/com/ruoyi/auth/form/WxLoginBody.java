package com.ruoyi.auth.form;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author 吴一博
 * @date 2023年07月07日 17:49
 * @Description
 */
public class WxLoginBody {
    private static final long serialVersionUID = 1L;

    /**
     * 账号(手机号/微信code)
     */

    @ApiModelProperty(value="账号(手机号/微信code)",required=false)
    @NotBlank(message = "登录账号不能为空")
    private String loginName;
    /**
     * 登录密码
     */

    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 用户手机号（微信登录需要传）
     */

    @ApiModelProperty(value = "用户手机号")
    private String telephone;
    /**
     *头像（微信登录需要传）
     */
    private String avatar;
    /**
     *性别（微信登录需要传）
     */

    private Integer gender;
    /**
     *昵称（微信登录需要传）
     */

    private String nickname;
    /**
     * 登录类型(1:pc;2:wx)
     */

    @ApiModelProperty(value = "登录类型(1:pc;2:wx)")
    @NotBlank(message = "登录类型不能为空")
    private String type;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
