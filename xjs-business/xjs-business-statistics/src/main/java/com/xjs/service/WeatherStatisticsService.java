package com.xjs.service;

import java.util.List;
import java.util.Map;

/**
 * 天气统计service接口
 * @author xiejs
 * @since 2022-01-26
 */
public interface WeatherStatisticsService {

    /**
     * 获取历史天气
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return key: value:
     */
    Map<String, List> historyWeather(String startDate, String endDate);

}
