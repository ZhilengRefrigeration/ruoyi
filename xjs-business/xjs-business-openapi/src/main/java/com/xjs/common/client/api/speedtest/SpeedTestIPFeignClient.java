package com.xjs.common.client.api.speedtest;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.SpeedTestIPFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.SPEED_TEST_IP;
import static com.xjs.consts.ApiConst.SPEED_TEST_IP_URL;

/**
 * https://myplugin.speedtest.cn/#/  测速网
 *
 * @author xiejs
 * @since 2022-01-17
 */
@FeignClient(name = "speedTestIP", url = SPEED_TEST_IP_URL,fallbackFactory = SpeedTestIPFeignFactory.class)
public interface SpeedTestIPFeignClient {

    @GetMapping()
    @ApiLog(name = SPEED_TEST_IP,
            url = SPEED_TEST_IP_URL,
            method = "Get")
    JSONObject IpApi(@RequestParam("ipv4") String requestBody);


}
