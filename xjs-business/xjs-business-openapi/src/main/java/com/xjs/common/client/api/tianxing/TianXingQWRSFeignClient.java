package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingQWRSFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.*;

/**
 * 天行数据全网热搜feign远程调用api
 * @author xiejs
 * @since 2022-01-10
 */
@FunctionalInterface
@FeignClient(name = "tianXingQWRS",url = TIANXING_TOPSEARCHALLNETWORK_URL,fallbackFactory = TianXingQWRSFeignFactory.class)
public interface TianXingQWRSFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_TOPSEARCHALLNETWORK_URL,
            url = TIANXING_PYQ_URL,
            method = "Get")
    JSONObject topSearchApi(@Param("key") String key);

}
