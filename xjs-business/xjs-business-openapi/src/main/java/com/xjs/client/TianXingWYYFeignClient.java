package com.xjs.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.aop.ApiLog;
import com.xjs.client.factory.TianXingWYYFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_WYY;
import static com.xjs.consts.ApiConst.TIANXING_WYY_URL;

/**
 * @author xiejs
 * @desc  天行数据网易云热评接口api调用
 * @create 2021-12-28
 */
@FunctionalInterface
@FeignClient(name = "tianXingWYY",url = TIANXING_WYY_URL,fallbackFactory = TianXingWYYFeignFactory.class)
public interface TianXingWYYFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_WYY,
            url = TIANXING_WYY_URL,
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
