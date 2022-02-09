package com.xjs.weather.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.common.client.api.roll.RollIPFeignClient;
import com.xjs.properties.RollProperties;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.utils.IPUtils;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.factory.IPFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.xjs.consts.ApiConst.*;
import static com.xjs.consts.RedisConst.IP_INFO;

/**
 * roll IP信息查询API工厂实现
 *
 * @author xiejs
 * @since 2022-01-15
 */
@Component
@Log4j2
public class RollIPFactory implements IPFactory<IPInfoVo> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollIPFeignClient rollIPFeignClient;
    @Autowired
    private RedisService redisService;

    @Override
    public IPInfoVo ipApi() {
        String ip = Optional.ofNullable(IPUtils.getV4IP()).orElse(LOCAL_IP);
        return ipApi(ip);
    }

    @Override
    public IPInfoVo ipApi(String ip) {
        RequestBody requestBody = new RequestBody();
        requestBody.setIp(ip);
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollIPFeignClient.ipApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject data = jsonObject.getJSONObject("data");
            return data.toJavaObject(IPInfoVo.class);
        } else {
            log.error("roll 获取ip信息，返回异常（ip不合法）");
            if (redisService.hasKey(IP_INFO)) {
                return (IPInfoVo) redisService.getCacheObject(IP_INFO);
            } else {
                return null;
            }
        }
    }

}
