package com.xjs.consts;

/**
 * redis key常量
 *
 * @author xiejs
 * @since 2021-12-30
 */
public class RedisConst {

    //----------------------key------------------------

    /**
     * 翻译字典常量key
     */
    public static final String TRAN_DICT = "tianxing:tran_dict";

    /**
     * 英语一言常量key
     */
    public static final String ONE_ENGLISH = "tianxing:one_english";

    /**
     * 热搜常量key
     */
    public static final String HOT = "tianxing:hot";

    /**
     * websocket常量key
     */
    public static final String WEBSOCKET = "websocket";

    /**
     * ip信息常量key
     */
    public static final String IP_INFO = "ip_info";

    /**
     * 实时天气常量信息key
     */
    public static final String NOW_WEATHER = "weather:now";

    /**
     * 预报天气常量信息key
     */
    public static final String FORECAST_WEATHER = "weather:forecast";

    /**
     * 爬虫记录循环次数常量信息：_36wallpaper
     */
    public static final String REPTILE_36_WALLPAPER_COUNT = "reptile:_36wallpaper.count";

    /**
     * 爬虫记录循环次数常量信息：weixin.sougou
     */
    public static final String REPTILE_WEIXIN_SOUGOU_COUNT = "reptile:weixin.sougou.count";

    /**
     * 爬虫记录循环次数常量信息：weixin.link
     */
    public static final String REPTILE_WEIXIN_LINK_COUNT = "reptile:weixin.link.count";
    ;

    //-------------------有效时间-----------------------
    public static final Integer TRAN_DICT_EXPIRE = 1;   //小时

    public static final Integer ONE_ENGLISH_EXPIRE = 3;   //分钟

    public static final Long HOT_EXPIRE = 10L;    //分钟

    public static final Long IP_INFO_EXPIRE = 30L;    //分钟

    public static final Long NOW_WHEATHER_EXPIRE = 10L;    //分钟

    public static final Long FORECAST_WHEATHER_EXPIRE = 10L;    //分钟


}
