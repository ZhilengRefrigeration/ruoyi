package com.xjs.copywriting.domain;

import lombok.Data;

/**
 * @author xiejs
 * @desc  文案api请求参数
 * @create 2021-12-27
 */
@Data
public class RequestBody {
    /**
     * 平台key密钥
     */
    private String key;


    /**
     * 请求类型 json/js/text
     */
    private String format;

    /**
     * 词
     */
    private String word;

    /**
     * 应用id
     */
    private String app_id;


    /**
     * 应用密钥
     */
    private String app_secret;

    /**
     * 返回值数量
     */
    private Integer count;

    /**
     * ip
     */
    private String ip;
}
