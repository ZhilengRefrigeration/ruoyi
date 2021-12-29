package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingJDTCFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.common.consts.ApiConst.TIANXING_JDTC;

/**
 * @author xiejs
 * @desc   天行数据经典台词接口api调用
 * @create 2021-12-29
 */
@FeignClient(name = "tianXingJDTC",url = "http://api.tianapi.com/dialogue/index?",fallbackFactory = TianXingJDTCFeignFactory.class)
public interface TianXingJDTCFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_JDTC,
            url = "http://api.tianapi.com/dialogue/index",
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
