package com.xjs.consts;

/**
 * redis key常量
 *
 * @author xiejs
 * @since 2021-12-30
 */
public class RedisConst {

    //----------------------bussiness-key------------------------

    /**
     * 翻译字典常量key
     */
    public static final String TRAN_DICT = "bussiness:tianxing:tran_dict";

    /**
     * 英语一言常量key
     */
    public static final String ONE_ENGLISH = "bussiness:tianxing:one_english";

    /**
     * 热搜常量key
     */
    public static final String HOT = "bussiness:tianxing:hot";

    /**
     * websocket常量key
     */
    public static final String WEBSOCKET = "websocket";

    /**
     * ip信息常量key
     */
    public static final String IP_INFO = "bussiness:ip_info";

    /**
     * 实时天气常量信息key
     */
    public static final String NOW_WEATHER = "bussiness:weather:now";

    /**
     * 预报天气常量信息key
     */
    public static final String FORECAST_WEATHER = "bussiness:weather:forecast";

    /**
     * 爬虫记录循环次数常量信息：_36wallpaper
     */
    public static final String REPTILE_36_WALLPAPER_COUNT = "bussiness:reptile:_36wallpaper.count";

    /**
     * 爬虫记录循环次数常量信息：weixin.sougou
     */
    public static final String REPTILE_WEIXIN_SOUGOU_COUNT = "bussiness:reptile:weixin.sougou.count";

    /**
     * 爬虫记录循环次数常量信息：weixin.link
     */
    public static final String REPTILE_WEIXIN_LINK_COUNT = "bussiness:reptile:weixin.link.count";

    /**
     *爬虫记录循环次数常量信息：zol.phone
     */
    public static final String REPTILE_ZOL_PHONE_COUNT = "bussiness:reptile:zol.phone.count";

    /**
     * 邮件记录状态常量信息key
     */
    public static final String MAIL_STATUS = "bussiness:mail:status";


    //--------------------------mall-key-----------------------------------

    /**
     * mallKey前缀
     */
    public static final String MALL_PREFIX = "mall:";

    /**
     * 三级分类后台key
     */
    public static final String CATALOG_AFTER = MALL_PREFIX + "catalog:after";

    /**
     * 三级分类前台key
     */
    public static final String CATALOG_BEFORE = MALL_PREFIX + "catalog:before";

    /**
     * Redis分布式锁key
     */
    public static final String LOCK = "lock";


    //-------------------有效时间-----------------------
    public static final Integer TRAN_DICT_EXPIRE = 1;   //小时

    public static final Integer ONE_ENGLISH_EXPIRE = 3;   //分钟

    public static final Long HOT_EXPIRE = 10L;    //分钟

    public static final Long IP_INFO_EXPIRE = 30L;    //分钟

    public static final Long NOW_WHEATHER_EXPIRE = 10L;    //分钟

    public static final Long FORECAST_WHEATHER_EXPIRE = 10L;    //分钟

    public static final Long LOCK_EXPIRE = 30L; //秒


}
