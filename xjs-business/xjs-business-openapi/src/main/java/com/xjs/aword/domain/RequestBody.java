package com.xjs.aword.domain;

import lombok.Data;

/**
 * 每日一言请求参数实体类
 * @author xiejs
 * @since 2022-01-08
 */
@Data
public class RequestBody {
    /**
     * 平台key密钥
     */
    private String key;

    /**
     * 每日一句是否随机（为空代表当天，0或1代表随机）
     */
    private Integer rand;

    /**
     * 指定时间，默认当天
     */
    private String date;

}
