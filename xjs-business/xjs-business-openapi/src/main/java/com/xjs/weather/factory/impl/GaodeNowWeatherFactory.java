package com.xjs.weather.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.gaode.GaodeWeatherFeignClient;
import com.xjs.properties.GaodeProperties;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.domain.NowWeather;
import com.xjs.weather.domain.RequestBody;
import com.xjs.weather.factory.WeatherFactory;
import com.xjs.weather.service.IPService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.*;

/**
 * 高德实时天气工厂实现
 *
 * @author xiejs
 * @since 2022-01-16
 */
@Component
@Log4j2
public class GaodeNowWeatherFactory implements WeatherFactory<NowWeather> {

    @Autowired
    private GaodeProperties gaodeProperties;
    @Autowired
    private GaodeWeatherFeignClient gaodeWeatherFeignClient;
    @Autowired
    private IPService ipService;


    @Override
    public NowWeather weatherApi() {
        RequestBody requestBody = new RequestBody();
        //获取城市编码
        IPInfoVo ipApiData = ipService.getIPApiData();
        requestBody.setKey(gaodeProperties.getKey())
                .setCity(ipApiData.getCityId())
                .setExtensions(GAODE_EXTENSIONS_BASE);
        JSONObject jsonObject = gaodeWeatherFeignClient.WeatherApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (INFOCODE_VALUE.equals(jsonObject.getString(INFOCODE))) {
                return jsonObject.getJSONArray(LIVES).getJSONObject(0).toJavaObject(NowWeather.class);
            }
        }
        return null;
    }
}
