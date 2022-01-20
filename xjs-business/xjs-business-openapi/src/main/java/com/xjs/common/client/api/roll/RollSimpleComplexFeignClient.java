package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollMobileBelongFeignFactory;
import com.xjs.common.client.factory.RollSimpleComplexFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.*;


/**
 * roll简繁转换接口api调用
 * @author xiejs
 * @since 2022-01-20
 */
@FeignClient(name = "rollSimpleComplex", url = ROLL_SIMPLE_COMPLEX_URL, fallbackFactory = RollSimpleComplexFeignFactory.class)
public interface RollSimpleComplexFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_SIMPLE_COMPLEX,
            url = ROLL_SIMPLE_COMPLEX_URL,
            method = "Get")
    JSONObject simpleComplexApi(@SpringQueryMap RequestBody requestBody);
}
