package com.ruoyi.common.security.service;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 * 
 * @author ruoyi
 */
@Component
public class TokenService
{
    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long EXPIRE_TIME = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginUser loginUser)
    {
        String token = IdUtils.fastUUID();
        Long userId = loginUser.getSysUser().getUserId();
        String userName = loginUser.getSysUser().getUserName();
        loginUser.setToken(token);
        loginUser.setUserid(userId);
        loginUser.setUsername(userName);
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", EXPIRE_TIME);
        return rspMap;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser()
    {
        return getLoginUser(SecurityUtils.getUserKey());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String userKey)
    {
        LoginUser user = null;
        try
        {
            if (StringUtils.isNotEmpty(userKey))
            {
                user = redisService.getCacheObject(getTokenKey(userKey));
            }
        }
        catch (Exception ignored)
        {
        }
        return user;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String userkey)
    {
        if (StringUtils.isNotEmpty(userkey))
        {
            redisService.deleteObject(getTokenKey(userkey));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String tokenKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(tokenKey, loginUser, EXPIRE_TIME, TimeUnit.MINUTES);
    }

    private String getTokenKey(String userKey)
    {
        return ACCESS_TOKEN + userKey;
    }
}