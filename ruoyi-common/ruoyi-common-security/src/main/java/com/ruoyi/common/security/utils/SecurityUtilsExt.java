package com.ruoyi.common.security.utils;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.system.api.model.LoginUser;

/**
 * @author Alan Scipio
 * created on 2024/2/6
 */
public class SecurityUtilsExt {

    /**
     * 获取用户ID
     */
    public static Long getUserIdFromRedis() {
        LoginUser loginUser = getLoginUserFromRedis();
        return loginUser == null ? null : loginUser.getUserid();
    }

    public static String getUserIdStr() {
        Long userId = getUserIdFromRedis();
        return userId == null ? "UNKNOWN" : String.valueOf(userId);
    }

    /**
     * 获取用户名称
     */
    public static String getUsernameFromRedis() {
        LoginUser loginUser = getLoginUserFromRedis();
        return loginUser == null ? "UNKNOWN" : loginUser.getUsername();
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUser getLoginUserFromRedis() {
        //从请求头里获取token
        String token = SecurityUtils.getToken();
        if (StringUtils.isNotEmpty(token)) {
            //从redis里获取用户信息
            LoginUser loginUser = AuthUtil.getLoginUser(token);
            if (loginUser != null) {
                //将用户信息存入线程变量（或更新）
                SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
                return loginUser;
            }
        }
        return null;
    }

}
