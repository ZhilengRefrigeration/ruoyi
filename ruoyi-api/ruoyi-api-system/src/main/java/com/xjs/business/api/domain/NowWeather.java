package com.xjs.business.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @since 2022-04-13
 */
@Data
public class NowWeather implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 省份名
     */
    private String province;

    /**
     * 城市名
     */
    private String city;

    /**
     * 城市编码
     */
    private String adcode;

    /**
     * 天气现象（汉字描述）
     */
    private String weather;

    /**
     * 实时气温，单位：摄氏度
     */
    private String temperature;

    /**
     * 风向描述
     */
    private String winddirection;

    /**
     * 风力级别，单位：级
     */
    private String windpower;

    /**
     * 空气湿度
     */
    private String humidity;

    /**
     * 数据发布的时间
     */
    private Date reporttime;


}
