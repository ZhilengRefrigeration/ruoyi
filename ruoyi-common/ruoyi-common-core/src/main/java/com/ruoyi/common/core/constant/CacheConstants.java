package com.ruoyi.common.core.constant;

/**
 * 缓存常量信息
 * 
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 7200;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 1200;

    /**
     * 密码最大错误次数
     */
    public final static int PASSWORD_MAX_RETRY_COUNT = 5;

    /**
     * 密码锁定时间，默认10（分钟）
     */
    public final static long PASSWORD_LOCK_TIME = 10;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 赛会推广的二维码缓存地址key
     */
    public static final String COMPETITION_SPREAD_AQR_CODE="competition:spread:aqr:code:";
    /**
     * 存放赛程循环赛的锁的key
     */
    public static final String ARRANGE_TEAM_GROUP_SCHEDULE="arrange:team:group:schedule:";

    /**
     * 小程序ACCESS_TOKEN缓存key
     */
    public static final String WX_APPLETS_REDIS_ACCESS_TOKEN_KEY="wx.applets.access.token.key";
    /**
     * 微信小程序--服务器验证专用token
     */
    public static final String WX_APPLETS_TOKEN="oHDqn56DWSxUHyiOnqLAyawfUj0k";
    /**
     * 微信小程序--开发者账号的appid
     */
    public static final String WX_APPLETS_APP_ID="wxd4300820f84a6d6b";
    /**
     * 微信小程序--开发者账号的AppSecret
     */
    public static final String WX_APPLETS_APP_SERCERT="16daf686025b3d9755976d79615b254f";
}
