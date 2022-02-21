package com.xjs._36wallpaper.consts;

/**
 * 36壁纸网配置参数常量类
 * @author xiejs
 * @since 2022-02-21
 */
public class _36wallpaperConst {

    /**
     * 是否全网爬虫
     */
    public static boolean INIT = false;

    /**
     * 是否下载图片带磁盘
     */
    public static boolean DOWNLOAD_IMG = false;

    /**
     * 图片保存到磁盘的路径
     */
    public static String PATH = "D:\\Dev\\WebCrawler\\36wallpaper";

    /**
     * redis的key
     */
    public static final String REDIS_KEY = "sys_config:xjs.webmagic._36wallpaper";

    /**
     * 系统配置表中的key
     */
    public static final String CONFIG_KEY = "xjs.webmagic._36wallpaper";

}
