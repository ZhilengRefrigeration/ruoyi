package com.xjs.apitools.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xjs.weather.domain.NowWeather;
import lombok.Data;

/**
 * api实时天气实体
 *
 * @author xiejs
 * @since 2022-01-18
 */
@Data
public class ApiNowWeather extends NowWeather {

    /**
     * 城市具体信息，比如 “广东省 深圳市”
     */
    private String address;

    /**
     * 温度
     */
    private String temp;

}
