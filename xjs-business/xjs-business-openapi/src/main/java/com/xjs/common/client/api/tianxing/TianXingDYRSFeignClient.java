package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingDYRSFeignFactory;
import com.xjs.common.client.factory.TianXingWXRSFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.*;

/**
 * @author xiejs
 * @since 2022-01-12
 */
@FunctionalInterface
@FeignClient(name = "tianXingDYRS",url = TIANXING_TOPSEARCHDOUYIN_URL,fallbackFactory = TianXingDYRSFeignFactory.class)
public interface TianXingDYRSFeignClient {
    @GetMapping
    @ApiLog(name = TIANXING_TOPSEARCHDOUYIN,
            url = TIANXING_TOPSEARCHDOUYIN_URL,
            method = "Get")
    JSONObject topSearchApi(@RequestParam("key") String key);
}
