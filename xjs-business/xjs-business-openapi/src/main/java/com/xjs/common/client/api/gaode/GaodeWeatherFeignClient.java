package com.xjs.common.client.api.gaode;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.GaodeWeatherFeignFactory;
import com.xjs.weather.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.GAODE_WEATHER;
import static com.xjs.consts.ApiConst.GAODE_WEATHER_URL;

/**
 * 高德天气预报api feign
 * @author xiejs
 * @since 2022-01-16
 */
@FeignClient(name = "gaodeWeather", url = GAODE_WEATHER_URL, fallbackFactory = GaodeWeatherFeignFactory.class)
public interface GaodeWeatherFeignClient {

    @GetMapping()
    @ApiLog(name = GAODE_WEATHER,
            url = GAODE_WEATHER_URL,
            method = "Get")
    JSONObject WeatherApi(@SpringQueryMap RequestBody requestBody);
}
