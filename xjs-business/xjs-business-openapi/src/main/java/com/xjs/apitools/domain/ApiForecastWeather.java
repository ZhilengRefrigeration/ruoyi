package com.xjs.apitools.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * api预报天气实体
 * @author xiejs
 * @since 2022-01-18
 */
@Data
public class ApiForecastWeather {

    /**
     * 城市具体信息，比如 “广东省 深圳市”
     */
    private String address;

    /**
     * 城市code
     */
    private String cityCode;

    /**
     * 此次天气发布时间
     */
    private String reportTime;

    /**
     * 今天及未来天气列表
     */
    private List<Forecasts> forecasts;

}
