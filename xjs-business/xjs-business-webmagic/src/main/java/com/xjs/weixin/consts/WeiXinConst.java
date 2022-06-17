package com.xjs.weixin.consts;

/**
 * 微信爬虫常量
 *
 * @author xiejs
 * @since 2022-03-17
 */
public class WeiXinConst {
    /**
     * 磁盘默认地址
     */
    public static final String PATH = "D:\\Dev\\WebCrawler\\Wechat";

    /**
     * redis的key
     */
    public static final String REDIS_KEY = "sys_config:xjs.webmagic.wechatPicture";

    public static final String REDIS_KEY_OFFICIAL = "sys_config:xjs.webmagic.official_accounts";

    public static final String REDIS_KEY_Y_2048 = "sys_config:xjs.webmagic.y2048";

    /**
     * 系统配置表中的key
     */
    public static final String CONFIG_KEY = "xjs.webmagic.wechatPicture";

    public static final String CONFIG_KEY_OFFICIAL = "xjs.webmagic.official_accounts";

    public static final String CONFIG_KEY_Y_2048 = "xjs.webmagic.y2048";



    public static final String JPEG = "jpeg";

    public static final String JPG = "jpg";

    public static final String PNG = "png";

    public static final String GIF = "gif";

    public static final String DOT = ".";

    /**
     * 文件大小（kb）
     */
    public static final Long SIZE_KB = 30L;


}
