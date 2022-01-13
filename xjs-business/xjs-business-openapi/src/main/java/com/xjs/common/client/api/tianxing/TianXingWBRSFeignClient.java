package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.TianXingWBRSFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.*;

/**
 * 天行微博热搜feign
 * @author xiejs
 * @since 2022-01-12
 */
@FunctionalInterface
@FeignClient(name = "tianXingWBRSF",url = TIANXING_TOPSEARCHWEIBO_URL,fallbackFactory = TianXingWBRSFeignFactory.class)
public interface TianXingWBRSFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_TOPSEARCHWEIBO,
            url = TIANXING_TOPSEARCHWEIBO_URL,
            method = "Get")
    JSONObject topSearchApi(@RequestParam("key") String key);
}
