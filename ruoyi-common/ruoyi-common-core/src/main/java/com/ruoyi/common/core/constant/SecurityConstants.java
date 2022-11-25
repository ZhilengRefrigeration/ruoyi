package com.ruoyi.common.core.constant;

/**
 * 权限相关通用常量
 * 
 * @author ruoyi
 */
public class SecurityConstants
{
    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION_HEADER = "authorization";

    /**
     * 请求来源
     */
    public static final String FROM_SOURCE = "from-source";

    /**
     * 内部请求
     */
    public static final String INNER = "inner";

    /**
     * 用户标识
     */
    public static final String USER_KEY = "user_key";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "login_user";

    /**
     * 角色权限
     */
    public static final String ROLE_PERMISSION = "role_permission";

    /**
     * 缓存在redis中的控制器路径与权限字符串对应的hash键
     */
    public static final String PATH_PERMISSION_MAP = "path_permission_map";

    /**
     * 缓存的角色名前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * 匿名角色（公共权限的角色名字）
     */
    public static final String ROLE_ANON = "ROLE_ANON";
}
