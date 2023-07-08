package com.ruoyi.system.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author 吴一博
 * @date 2023年07月07日 17:53
 * @Description
 */
public class WxLoginUser {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 微信多平台用户唯一ID
     */
    private String unionid;

    /**
     * 微信用户唯一OPEN_ID
     */
    private String openId;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 角色ID列表
     */
    private List<String> roleCodes;
    /**
     * token
     */

    @ApiModelProperty(value = "token")
    private String accessToken;
    /**
     *刷新token
     */

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    /**
     *用户所拥有的菜单权限(给前端控制菜单和按钮的显示和隐藏)
     */
    /*@Setter
    @Getter
    @ApiModelProperty(value = "用户所拥有的菜单权限(给前端控制菜单和按钮的显示和隐藏)")
    private List<PermissionRespNode> list;*/

    /**
     * wx 登录 后返回的 session_key
     */
    private String sessionKey;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
