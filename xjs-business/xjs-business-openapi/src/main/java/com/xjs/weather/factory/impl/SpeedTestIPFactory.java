package com.xjs.weather.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.speedtest.SpeedTestIPFeignClient;
import com.xjs.utils.IPUtils;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.factory.IPFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.xjs.consts.ApiConst.*;

/**
 * 测速网 IP信息查询API工厂实现 (主要获取ipv6)
 * @author xiejs
 * @since 2022-01-17
 */
@Component
public class SpeedTestIPFactory implements IPFactory<IPInfoVo> {

    @Autowired
    private SpeedTestIPFeignClient speedTestIPFeignClient;

    @Override
    @Deprecated
    public IPInfoVo ipApi() {
        String v4IP = IPUtils.getV4IP();
        String ip = Optional.ofNullable(v4IP).orElse(LOCAL_IP);
        JSONObject jsonObject = speedTestIPFeignClient.IpApi(ip);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == SPEED_TEST_CODE_SUCCESS.intValue()) {
            JSONObject data = jsonObject.getJSONObject("data");
            IPInfoVo ipInfoVo = new IPInfoVo();
            ipInfoVo.setCity(data.getString("city"));
            ipInfoVo.setIp(data.getString("ip"));
            ipInfoVo.setIsp(data.getString("isp"));
            ipInfoVo.setProvince(data.getString("province"));
            System.out.println("json!!!"+data.toJSONString());
            return ipInfoVo;
        }
        return null;
    }

    @Override
    public IPInfoVo ipApi(String ip) {
        return null;
    }
}
