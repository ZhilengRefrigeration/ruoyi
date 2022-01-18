package com.xjs.weather.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 高德预报天气实体
 *
 * @author xiejs
 * @since 2022-01-16
 */
@Data
public class ForecastWeather implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 城市编码
     */
    private String adcode;

    /**
     * 省份名称
     */
    private String province;

    /**
     *预报发布时间
     */
    private String reporttime;

    /**
     * 预报数据list结构，元素cast,按顺序为当天、第二天、第三天的预报数据
     */
    private List<Casts> casts;

}
