package com.xjs.weather.service;

import com.xjs.weather.domain.NowWeather;

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


}
