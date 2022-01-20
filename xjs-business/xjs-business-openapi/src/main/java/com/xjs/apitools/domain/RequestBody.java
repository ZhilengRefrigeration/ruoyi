package com.xjs.apitools.domain;

import lombok.Data;

/**
 *
 * @author xiejs
 * @since 2022-01-18
 */
@Data
public class RequestBody {

    /**
     * 应用id
     */
    private String app_id;


    /**
     * 应用密钥
     */
    private String app_secret;

    /**
     * 目标手机
     */
    private String mobile;

    /**
     * 目标城市
     */
    private String city;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 是否需要详情，0：不需要详情 1：需要详情 默认值 0 可不传  <br>
     * 1 简体转繁体 2 繁体转简体
     */
    private Integer type;


    /**
     * 待转换的内容 <br>
     * 被查询的汉字内容
     */
    private String content;


}
