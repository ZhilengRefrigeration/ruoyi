package com.xjs.weather.service;

import com.xjs.weather.domain.ForecastWeather;
import com.xjs.weather.domain.NowWeather;

import java.util.List;
import java.util.Map;

/**
 * 天气服务
 * @author xiejs
 * @since 2022-01-16
 */
public interface WeatherService {

    /**
     * 保存实时天气并放入缓存
     * @return NowWeather
     */
    NowWeather saveNowWeather();

    /**
     * 只保存
     * @return NowWeather
     */
    NowWeather save();

    /**
     * 预报天气放入缓存
     * @return ForecastWeather
     */
    ForecastWeather cacheForecastWeather();


    /**
     * 获取历史天气
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return key: value:
     */
    Map<String, List> getHistoryWeather(String startDate, String endDate);
}
