package com.xjs.weather.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.common.client.api.roll.RollIPFeignClient;
import com.xjs.config.RollProperties;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.utils.IPUtil;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.factory.IPFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public IPInfoVo IpApi() {
        RequestBody requestBody = new RequestBody();
        try {
            requestBody.setIp(IPUtil.getV4IP());
        } catch (Exception e) {
            requestBody.setIp("127.0.0.1");
        }
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollIPFeignClient.IpApi(requestBody);
        if (!jsonObject.containsKey("error") && jsonObject.getInteger("code") == 1) {
            JSONObject data = jsonObject.getJSONObject("data");
            return data.toJavaObject(IPInfoVo.class);
        } else {
            log.error("天行全网热搜服务调用成功，但返回异常");
            if (redisService.hasKey(IP_INFO)){
                return (IPInfoVo) redisService.getCacheObject(IP_INFO);
            }else {
                return null;
            }
        }
    }

}
