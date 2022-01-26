package com.xjs.weather.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.exception.BusinessException;
import com.xjs.weather.domain.ForecastWeather;
import com.xjs.weather.domain.NowWeather;
import com.xjs.weather.factory.WeatherFactory;
import com.xjs.weather.mapper.NowWeatherMapper;
import com.xjs.weather.service.WeatherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.*;

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
    @Autowired
    private WeatherFactory<ForecastWeather> gaodeForecastWeatherFactory;
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
                this.checkExistSave(nowWeather);
                redisService.setCacheObject(NOW_WEATHER, nowWeather, NOW_WHEATHER_EXPIRE, TimeUnit.MINUTES);
                return nowWeather;
            } else {
                throw new BusinessException("获取实时天气数据为空");
            }
        } else {
            return (NowWeather) redisService.getCacheObject(NOW_WEATHER);
        }
    }

    @Override
    public NowWeather save() {
        NowWeather nowWeather = Optional.ofNullable(gaodeNowWeatherFactory.weatherApi()).orElseGet(NowWeather::new);
        this.checkExistSave(nowWeather);
        return nowWeather;
    }

    @Override
    public ForecastWeather cacheForecastWeather() {
        if (redisService.hasKey(FORECAST_WEATHER)) {
            return (ForecastWeather) redisService.getCacheObject(FORECAST_WEATHER);
        }
        ForecastWeather forecastWeather = gaodeForecastWeatherFactory.weatherApi();
        if (Objects.nonNull(forecastWeather)) {
            redisService.setCacheObject(FORECAST_WEATHER, forecastWeather, FORECAST_WHEATHER_EXPIRE, TimeUnit.MINUTES);
            return forecastWeather;
        }else {
            throw new BusinessException("获取预报天气数据为空");
        }
    }

    @Override
    public Map<String, List> getHistoryWeather(String startDate, String endDate) {
        List<NowWeather> weatherList = nowWeatherMapper.selectList(new QueryWrapper<NowWeather>()
                .between("create_time", startDate, endDate));

        ArrayList<String> dateTime = new ArrayList<>();
        ArrayList<String> temperature = new ArrayList<>();
        weatherList.forEach(weather ->{
            dateTime.add(DateUtil.format(weather.getReporttime(),"MM-dd HH"));
            temperature.add(weather.getTemperature());
        });

        Map<String, List> listMap = new HashMap<>();
        listMap.put("reportTime", dateTime);
        listMap.put("temperature", temperature);

        return listMap;
    }


    /**
     * 校验当前天气数据数据库是否存在
     *
     * @param nowWeather 天气数据
     */
    private void checkExistSave(NowWeather nowWeather) {
        Date reporttime = nowWeather.getReporttime();
        String dateTime = DateUtil.formatDateTime(reporttime);
        NowWeather selectOne = nowWeatherMapper.selectOne(new QueryWrapper<NowWeather>().eq("reporttime", dateTime));
        if (Objects.isNull(selectOne)) {
            if(StringUtils.isNotBlank(nowWeather.getTemperature()))
            nowWeatherMapper.insert(nowWeather);
        }
    }


}
