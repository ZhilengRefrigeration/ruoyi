package com.xjs.weather.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * IP信息实体
 * @author xiejs
 * @since 2022-01-15
 */
@Data
public class IPInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 省份
     */
    private String province;

    /**
     * 省份Id
     */
    private String code;

    /**
     * 城市
     */
    private String city;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 网络服务商名称 例如 电信
     */
    private String isp;

    /**
     * 拼接好的描述信息
     */
    private String desc;

}
