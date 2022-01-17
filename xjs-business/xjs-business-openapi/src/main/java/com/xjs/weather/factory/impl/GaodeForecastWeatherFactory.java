package com.xjs.weather.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.gaode.GaodeWeatherFeignClient;
import com.xjs.config.GaodeProperties;
import com.xjs.weather.domain.ForecastWeather;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.domain.RequestBody;
import com.xjs.weather.factory.WeatherFactory;
import com.xjs.weather.service.IPService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.*;

/**
 * 高德预报天气工厂实现
 * @author xiejs
 * @since 2022-01-17
 */
@Component
@Log4j2
public class GaodeForecastWeatherFactory implements WeatherFactory<ForecastWeather> {

    @Autowired
    private GaodeProperties gaodeProperties;
    @Autowired
    private GaodeWeatherFeignClient gaodeWeatherFeignClient;
    @Autowired
    private IPService ipService;


    @Override
    public ForecastWeather weatherApi() {
        RequestBody requestBody = new RequestBody();
        //获取城市编码
        IPInfoVo ipApiData = ipService.getIPApiData();
        requestBody.setKey(gaodeProperties.getKey())
                .setCity(ipApiData.getCityId())
                .setExtensions(GAODE_EXTENSIONS_ALL);
        JSONObject jsonObject = gaodeWeatherFeignClient.WeatherApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (INFOCODE_VALUE.equals(jsonObject.getString(INFOCODE))) {
                JSONObject forecasts = jsonObject.getJSONArray(FORECASTS).getJSONObject(0);
                return forecasts.toJavaObject(ForecastWeather.class);
            }
        }
        return null;
    }
}
