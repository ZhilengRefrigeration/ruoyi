package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingPYQFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejs
 * @desc  天行数据朋友圈文案接口api调用
 * @create 2021-12-27
 */
@FunctionalInterface
@FeignClient(name = "tianXingPYQ",url = "http://api.tianapi.com/pyqwenan/index",fallbackFactory = TianXingPYQFeignFactory.class)
public interface TianXingPYQFeignClient {

    @GetMapping
    @ApiLog(name = "tianXing",
            url = "http://api.tianapi.com/pyqwenan/index",
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
