package com.xjs.weixin.service;

/**
 * 微信文章链接service接口
 * @author xiejs
 * @since 2022-03-17
 */
public interface WeiXinLinkService {

    /**
     * 爬虫获取微信文章图片
     * @return 布尔
     * @param link 链接地址
     */
    Boolean getPicture(String link);

    /**
     * 获取配置参数（磁盘地址）
     * @return str
     */
    String getSettings();

    /**
     * 修改配置参数（磁盘地址）
     * @param path 磁盘地址
     * @return 布尔
     */
    boolean updateSettings(String path);

    /**
     * 重置配置参数
     * @return 布尔
     */
    boolean restSettings();
}
