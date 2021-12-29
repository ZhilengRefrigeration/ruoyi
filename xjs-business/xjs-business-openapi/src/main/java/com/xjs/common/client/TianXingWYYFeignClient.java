package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingWYYFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.common.consts.ApiConst.TIANXING_WYY;

/**
 * @author xiejs
 * @desc  天行数据网易云热评接口api调用
 * @create 2021-12-28
 */
@FunctionalInterface
@FeignClient(name = "tianXingWYY",url = "http://api.tianapi.com/hotreview/index",fallbackFactory = TianXingWYYFeignFactory.class)
public interface TianXingWYYFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_WYY,
            url = "http://api.tianapi.com/hotreview/index",
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
