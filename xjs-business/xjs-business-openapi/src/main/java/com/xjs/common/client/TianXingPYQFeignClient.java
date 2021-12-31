package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingPYQFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_PYQ;
import static com.xjs.consts.ApiConst.TIANXING_PYQ_URL;

/**
 * @author xiejs
 * @desc  天行数据朋友圈文案接口api调用
 * @create 2021-12-27
 */
@FunctionalInterface
@FeignClient(name = "tianXingPYQ",url = TIANXING_PYQ_URL,fallbackFactory = TianXingPYQFeignFactory.class)
public interface TianXingPYQFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_PYQ,
            url = TIANXING_PYQ_URL,
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
