package com.xjs.consts;

/**
 * @author xiejs
 * @desc redis key常量
 * @create 2021-12-30
 */
public class RedisConst {

    //----------------------key------------------------

    /**
     * 翻译字典常量key
     */
    public static final String TRAN_DICT = "tranDict";

    /**
     * 英语一言常量key
     */
    public static final String ONE_ENGLISH = "oneEnglish";

    /**
     * 热搜常量key
     */
    public static final String HOT = "hot";

    /**
     * websocket常量key
     */
    public static final String WEBSOCKET = "WEBSOCKET";

    /**
     * ip信息常量key
     */
    public static final String IP_INFO = "IPInfo";


    //-------------------有效时间-----------------------
    public static final Integer TRAN_DICT_EXPIRE = 1;   //小时

    public static final Integer ONE_ENGLISH_EXPIRE = 3;   //分钟

    public static final Long HOT_EXPIRE = 10L;    //分钟

    public static final Long IP_INFO_EXPIRE = 30L;    //分钟


}
