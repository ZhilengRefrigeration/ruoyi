package com.xjs.consts;

/**
 * @author xiejs
 * @desc api名称常量类
 * @create 2021-12-28
 */
public class ApiConst {

    //--------------------name------------------------------

    public static final String YOUDAO_FY= "有道翻译";

    public static final String BAIDU_FY= "百度翻译";

    public static final String ROLL_FY= "ROLL翻译";

    public static final String TIANXING_PYQ= "天行-朋友圈";

    public static final String TIANXING_WYY= "天行-网易云";

    public static final String TIANXING_JDTC= "天行-经典台词";

    public static final String TIANXING_MMMY= "天行-名人名言";

    public static final String TIANXING_TRAN_DICT= "天行-翻译字典";

    public static final String TIANXING_ONE_ENGLISH= "天行-英语一言";

    public static final String ROLL_A_WORD= "ROLL-每日一句";

    public static final String TIANXING_A_WORD= "天行-每日一句";

    public static final String TIANXING_TOPSEARCHALLNETWORK= "天行-全网热搜榜";

    public static final String TIANXING_TOPSEARCHWECHAT= "天行-微信热搜榜";

    public static final String TIANXING_TOPSEARCHBAIDU= "天行-百度热搜榜";

    public static final String TIANXING_TOPSEARCHWEIBO= "天行-微博热搜榜";

    public static final String TIANXING_TOPSEARCHDOUYIN= "天行-抖音热搜榜";

    public static final String ROLL_IP= "ROLL-IP信息";

    public static final String GAODE_WEATHER= "高德-天气预报";






    //-------------------url------------------------------

    public static final String BAIDU_FY_URL= "http://api.fanyi.baidu.com/api/trans/vip/translate";

    public static final String YOUDAO_FY_URL= "http://fanyi.youdao.com/translate";

    public static final String ROLL_FY_URL= "https://www.mxnzp.com/api/convert/translate";

    public static final String TIANXING_PYQ_URL= "http://api.tianapi.com/pyqwenan/index";

    public static final String TIANXING_WYY_URL= "http://api.tianapi.com/hotreview/index";

    public static final String TIANXING_JDTC_URL= "http://api.tianapi.com/dialogue/index";

    public static final String TIANXING_MMMY_URL= "http://api.tianapi.com/mingyan/index";

    public static final String TIANXING_TRAN_DICT_URL= "http://api.tianapi.com/enwords/index";

    public static final String TIANXING_ONE_ENGLISH_URL= "http://api.tianapi.com/ensentence/index";

    public static final String ROLL_A_WORD_URL= "https://www.mxnzp.com/api/daily_word/recommend";

    public static final String TIANXING_A_WORD_URL= "http://api.tianapi.com/everyday/index";

    public static final String TIANXING_TOPSEARCHALLNETWORK_URL= "http://api.tianapi.com/networkhot/index";

    public static final String TIANXING_TOPSEARCHWECHAT_URL= "http://api.tianapi.com/wxhottopic/index";

    public static final String TIANXING_TOPSEARCHBAIDU_URL= "http://api.tianapi.com/nethot/index";

    public static final String TIANXING_TOPSEARCHWEIBO_URL= "http://api.tianapi.com/weibohot/index";

    public static final String TIANXING_TOPSEARCHDOUYIN_URL= "http://api.tianapi.com/douyinhot/index";

    public static final String ROLL_IP_URL= "https://www.mxnzp.com/api/ip/aim_ip";

    /**
     * 接口文档：
     * https://lbs.amap.com/api/webservice/guide/api/weatherinfo
     */
    public static final String GAODE_WEATHER_URL= "https://restapi.amap.com/v3/weather/weatherInfo";




    //-----------------------api请求参数常量-----------------------------

    /**
     * 高德Extensions请求参数条件
     */
    public static final String GAODE_EXTENSIONS_ALL="all" ;
    public static final String GAODE_EXTENSIONS_BASE="base" ;











    //-----------------------api响应参数-----------------------------------


    /**
     * roll平台返回值code成功参数
     */
    public static final Integer ROLL_CODE_SUCCESS=1 ;




    //---------------------自定义相关请求响应常量----------------------------

    /**
     * 降级返回结果key
     */
    public static final String DEMOTE_ERROR= "error";







}
