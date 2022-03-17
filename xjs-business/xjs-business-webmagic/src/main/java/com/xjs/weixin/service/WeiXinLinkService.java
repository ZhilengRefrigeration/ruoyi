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
}
