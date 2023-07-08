package com.ruoyi.common.core.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * www主域
     */
    public static final String WWW = "www.";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi:";

    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap:";

    /**
     * LDAPS 远程方法调用
     */
    public static final String LOOKUP_LDAPS = "ldaps:";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 登录成功状态
     */
    public static final String LOGIN_SUCCESS_STATUS = "0";

    /**
     * 登录失败状态
     */
    public static final String LOGIN_FAIL_STATUS = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
     */
    public static final String[] JOB_WHITELIST_STR = { "com.ruoyi" };
    /**
     * 定时任务违规的字符
     */
    public static final String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", "com.ruoyi.common.core.utils.file" };


    /**
     * 用户ID key
     */
    public static final String JWT_USER_ID="user_id";
    /**
     * 用户ID key
     */
    public static final String JWT_USER_NAME="user_name";
    /**
     * 用户ID key
     */
    public static final String JWT_LOGIN_NAME="login_name";

    /**
     * 真实姓名
     */
    public static final String JWT_REAL_NAME="real_name";

    /**
     * 微信多平台唯一ID key
     */
    public static final String JWT_UNIONID="unionid";
    /**
     * 登录微信后的session_key
     */
    public static final String JWT_SESSION_KEY = "session_key";

    /**
     * 登录微信后的open_id
     */
    public static final String JWT_OPEN_ID = "open_id";
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

    /**
     * 公众号ACCESS_TOKEN缓存key
     */
    public static final String WX_OFFICIAL_ACCOUNT_REDIS_ACCESS_TOKEN_KEY="wx.officialAccount.access.token.key";
    /**
     * 微信公众号--服务器验证专用token
     */
    public static final String WX_OFFICIAL_ACCOUNT_TOKEN="oHDqn56DWSxUHyiOnqLAyawfUj0k";
    /**
     * 微信公众号--开发者账号的appid
     */
    public static final String WX_OFFICIAL_ACCOUNT_APPID ="wx42f8776ad330e623";
    /**
     * 微信公众号--开发者账号的AppSecret
     */
    public static final String WX_OFFICIAL_ACCOUNT_APPSECRET="6dd7ea55e17ec30df20164f1de1e9e5f";

    /**
     * 赛会： 赛会创建-防重提交缓存key
     */
    public static final String COMPETITION_CREATE_KEY = "competition:create:code:";
    /**
     * 赛会： 赛会创建时保存的短信验证码缓存key
     */
    public static final String ESTABLISH_COMPETITION_SMS_CAPTCHA = "competition:verifyCode:";

    /**
     * 赛会： 球队申请加入赛会-短信验证码缓存key
     */
    public static final String COMPETITION_TEAM_CAPTCHA = "competition:team:verifyCode:";
    /**
     * 赛会：球队-球员申请加入球队-短信验证码缓存key
     */
    public static final String COMPETITION_TEAM_MEMBER_CAPTCHA = "competition:team:member:verifyCode:";
    /**
     * 球队加入验证码过期时间-有效期 (秒)
     */
    public static final Long COMPETITION_TEAM_MEMBER_CAPTCHA_EXPIRES = 300L;
    /**
     * 泡泡短信平台-url前缀
     */
    public static final String SMS_PAOPAO_URL = "http://47.94.229.220:7862/sms";
    /**
     * 泡泡短信平台-account
     */
    public static final String SMS_PAOPAO_ACCOUNT = "911136";
    /**
     * 泡泡短信平台-密码 password
     */
    public static final String SMS_PAOPAO_PASSWORD = "MMfZ3p";
    /**
     * 泡泡短信平台-接入号 extno
     */
    public static final String SMS_PAOPAO_EXTNO = "106901911136";
    /**
     * 泡泡短信平台-有效期 (秒)
     */
    public static final Long SMS_PAOPAO_EXPIRES = 900L;
    /**
     * 短信签名
     */
    public static final String SMS_PAOPAO_SIGN="【篮球Zone】";
    /**
     * 存放赛程循环赛的锁的key
     */
    public static final String ARRANGE_TEAM_GROUP_SCHEDULE="arrange:team:group:schedule:";
    /**
     * Redis 存放乐橙云 管理员token
     */
    public static final String LECHANGE_ACCESSTOKEN = "lechange:accesstoken";
    /**
     * Redis 存放乐橙云 轻应用播放token
     */
    public static final String LECHANGE_LIVE_KITTOKEN = "lechange:live:kittoken";
}
