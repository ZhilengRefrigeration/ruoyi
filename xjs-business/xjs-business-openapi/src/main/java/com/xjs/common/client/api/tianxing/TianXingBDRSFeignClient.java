package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.TianXingBDRSFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.*;

/**
 * 天行数据百度热搜feign远程调用api
 * @author xiejs
 * @since 2022-01-11
 */
@FunctionalInterface
@FeignClient(name = "tianXingBDRS",url = TIANXING_TOPSEARCHBAIDU_URL,fallbackFactory = TianXingBDRSFeignFactory.class)
public interface TianXingBDRSFeignClient {
    @GetMapping
    @ApiLog(name = TIANXING_TOPSEARCHBAIDU,
            url = TIANXING_TOPSEARCHBAIDU_URL,
            method = "Get")
    JSONObject topSearchApi(@RequestParam("key") String key);
}
