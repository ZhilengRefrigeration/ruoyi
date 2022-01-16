package com.xjs.weather.domain;

import lombok.Data;

/**
 * 预报天气某天数据
 * @author xiejs
 * @since 2022-01-16
 */
@Data
public class Casts {

    /**
     *日期
     */
    private String date;

    /**
     *星期几
     */
    private String week;

    /**
     *白天天气现象
     */
    private String dayweather;

    /**
     *晚上天气现象
     */
    private String nightweather;

    /**
     *白天温度
     */
    private String daytemp;

    /**
     *晚上温度
     */
    private String nighttemp;

    /**
     *白天风向
     */
    private String daywind;

    /**
     *晚上风向
     */
    private String nightwind;

    /**
     *白天风力
     */
    private String daypower;

    /**
     *晚上风力
     */
    private String nightpower;



}
