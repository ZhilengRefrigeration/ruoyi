package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.api.roll.RollWeatherFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 *  roll 天气服务api降级处理
 * @author xiejs
 * @since 2022-01-18
 */
@Component
@Log4j2
public class RollWeatherFeignFactory implements FallbackFactory<RollWeatherFeignClient> {
    @Override
    public RollWeatherFeignClient create(Throwable cause) {
        return new RollWeatherFeignClient() {
            @Override
            public JSONObject nowWeatherApi(RequestBody requestBody,String city) {
                log.error("api模块roll 实时天气服务调用失败:{},执行降级处理", cause.getMessage());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(DEMOTE_ERROR, R.FAIL);
                return jsonObject;
            }

            @Override
            public JSONObject forecastWeatherApi(RequestBody requestBody, String city) {
                log.error("api模块roll 预报天气服务调用失败:{},执行降级处理", cause.getMessage());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(DEMOTE_ERROR, R.FAIL);
                return jsonObject;
            }
        };
    }
}
