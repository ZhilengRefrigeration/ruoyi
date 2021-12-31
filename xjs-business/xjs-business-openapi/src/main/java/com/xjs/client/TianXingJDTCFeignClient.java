package com.xjs.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.aop.ApiLog;
import com.xjs.client.factory.TianXingJDTCFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_JDTC;
import static com.xjs.consts.ApiConst.TIANXING_JDTC_URL;

/**
 * @author xiejs
 * @desc   天行数据经典台词接口api调用
 * @create 2021-12-29
 */
@FeignClient(name = "tianXingJDTC",url = TIANXING_JDTC_URL,fallbackFactory = TianXingJDTCFeignFactory.class)
public interface TianXingJDTCFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_JDTC,
            url = TIANXING_JDTC_URL,
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
