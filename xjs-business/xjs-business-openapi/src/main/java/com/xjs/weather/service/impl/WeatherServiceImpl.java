package com.xjs.weather.service.impl;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.exception.BusinessException;
import com.xjs.weather.domain.NowWeather;
import com.xjs.weather.factory.WeatherFactory;
import com.xjs.weather.mapper.NowWeatherMapper;
import com.xjs.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.NOW_WEATHER;
import static com.xjs.consts.RedisConst.NOW_WHEATHER_EXPIRE;

/**
 * 天气服务实现
 *
 * @author xiejs
 * @since 2022-01-16
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherFactory<NowWeather> gaodeNowWeatherFactory;
    @Resource
    private NowWeatherMapper nowWeatherMapper;
    @Autowired
    private RedisService redisService;


    @Override
    @Transactional
    public NowWeather saveNowWeather() {
        if (!redisService.hasKey(NOW_WEATHER)) {
            NowWeather nowWeather = gaodeNowWeatherFactory.weatherApi();
            if (Objects.nonNull(nowWeather)) {
                nowWeatherMapper.insert(nowWeather);
                redisService.setCacheObject(NOW_WEATHER, nowWeather, NOW_WHEATHER_EXPIRE, TimeUnit.MINUTES);
                return nowWeather;
            } else {
                throw new BusinessException("获取天气数据为空");
            }
        } else {
            return (NowWeather) redisService.getCacheObject(NOW_WEATHER);
        }
    }


}
