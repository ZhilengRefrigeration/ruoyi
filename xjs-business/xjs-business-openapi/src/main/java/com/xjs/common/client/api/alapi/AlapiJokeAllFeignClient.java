package com.xjs.common.client.api.alapi;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.AlapiJokeAllFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.ALAPI_JOKE_ALL;
import static com.xjs.consts.ApiConst.ALAPI_JOKE_ALL_URL;

/**
 * alapi平台笑话大全feign
 * @author xiejs
 * @since 2022-02-15
 */
@FeignClient(name = "alapiJokeAll", url = ALAPI_JOKE_ALL_URL, fallbackFactory = AlapiJokeAllFeignFactory.class)
public interface AlapiJokeAllFeignClient {
    @GetMapping( headers ={ "Accept-Encoding=''"})//解决响应乱码
    @ApiLog(name = ALAPI_JOKE_ALL,
            url = ALAPI_JOKE_ALL_URL,
            method = "Get")
    JSONObject alapiJokeAllApi(@RequestParam("token") String token);
}
