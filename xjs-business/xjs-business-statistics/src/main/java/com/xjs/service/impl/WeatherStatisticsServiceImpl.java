package com.xjs.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xjs.business.api.RemoteWeatherFeign;
import com.xjs.service.WeatherStatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 天气统计service接口实现
 * @author xiejs
 * @since 2022-01-26
 */
@Service
public class WeatherStatisticsServiceImpl implements WeatherStatisticsService {

    @Autowired
    private RemoteWeatherFeign remoteWeatherFeign;


    @Override
    public Map<String, List> historyWeather(String startDate, String endDate) {
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            startDate = DateUtil.today() + " 00:00:00";
            endDate = DateUtil.today() + " 23:59:59";
        }
        return remoteWeatherFeign.getHistoryWeatherForRPC(startDate, endDate).getData();
    }
}
