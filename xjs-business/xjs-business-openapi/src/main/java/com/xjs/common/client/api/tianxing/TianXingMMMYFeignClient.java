package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingMMMYFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_MMMY;
import static com.xjs.consts.ApiConst.TIANXING_MMMY_URL;

/**
 * @author xiejs
 * @desc  天行数据名人名言接口api调用
 * @create 2021-12-29
 */
@FeignClient(name = "tianXingMMMY",url = TIANXING_MMMY_URL,fallbackFactory = TianXingMMMYFeignFactory.class)
public interface TianXingMMMYFeignClient {
    @GetMapping
    @ApiLog(name = TIANXING_MMMY,
            url = TIANXING_MMMY_URL,
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
