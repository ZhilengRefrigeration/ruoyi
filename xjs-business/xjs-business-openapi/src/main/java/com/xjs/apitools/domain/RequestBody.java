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
}
