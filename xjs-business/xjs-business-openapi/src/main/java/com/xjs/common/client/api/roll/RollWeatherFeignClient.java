package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollWeatherFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.xjs.consts.ApiConst.*;

/**
 * roll 天气预报api调用feign
 * @author xiejs
 * @since 2022-01-18
 */
@FeignClient(name = "rollWeather", url = ROLL_WEATHER_URL, fallbackFactory = RollWeatherFeignFactory.class)
public interface RollWeatherFeignClient {

    @GetMapping("/current/{city}")
    @ApiLog(name = ROLL_NOW_WEATHER,
            url = ROLL_WEATHER_URL+"/current",
            method = "Get")
    JSONObject nowWeatherApi(@SpringQueryMap RequestBody requestBody, @PathVariable("city")String city);


    @GetMapping("/forecast/{city}")
    @ApiLog(name = ROLL_FORECAST_WEATHER,
            url = ROLL_WEATHER_URL+"/forecast",
            method = "Get")
    JSONObject forecastWeatherApi(@SpringQueryMap RequestBody requestBody,@PathVariable("city")String city);

}
