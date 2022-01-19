package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiNowWeather;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollWeatherFeignClient;
import com.xjs.config.RollProperties;
import com.xjs.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.*;

/**
 * roll天气预报api工厂实现
 * @author xiejs
 * @since 2022-01-18
 */
@Component
@Log4j2
public class RollNowWeatherFactory implements ApiToolsFactory<ApiNowWeather, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollWeatherFeignClient rollWeatherFeignClient;


    @Override
    public ApiNowWeather apiData(RequestBody requestBody) {
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollWeatherFeignClient.nowWeatherApi(requestBody, requestBody.getCity());
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            return jsonData.toJavaObject(ApiNowWeather.class);
        }else if (jsonObject.getInteger("code") == ROLL_CODE_ERROR.intValue()) {
            throw new ApiException("未找到对应城市，请检查后重试");
        }
        return null;
    }
}
