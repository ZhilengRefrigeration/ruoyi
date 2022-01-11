package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingQWRSFeignFactory;
import com.xjs.common.client.factory.TianXingWXRSFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.*;

/**
 * 天行微信热搜feign
 * @author xiejs
 * @since 2022-01-11
 */

@FunctionalInterface
@FeignClient(name = "tianXingWXRS",url = TIANXING_TOPSEARCHALLWECHAT_URL,fallbackFactory = TianXingWXRSFeignFactory.class)
public interface TianXingWXRSFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_TOPSEARCHWECHAT,
            url = TIANXING_TOPSEARCHALLWECHAT_URL,
            method = "Get")
    JSONObject topSearchApi(@RequestParam("key") String key);
}
