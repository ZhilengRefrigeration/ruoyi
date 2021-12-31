package com.xjs.consts;

/**
 * @author xiejs
 * @desc  redis key常量
 * @create 2021-12-30
 */
public class RedisConst {

    //----------------------key------------------------

    /**
     * 翻译字典常量key
     */
    public static final String TRAN_DICT= "tranDict";

    /**
     * 英语一言常量key
     */
    public static final String ONE_ENGLISH= "oneEnglish";


    //-------------------有效时间-----------------------
    public static final Integer TRAN_DICT_EXPIRE = 7;   //天

    public static final Integer ONE_ENGLISH_EXPIRE = 3;   //分钟

}
