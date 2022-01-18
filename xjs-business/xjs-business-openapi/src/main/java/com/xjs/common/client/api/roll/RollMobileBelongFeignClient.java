package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollMobileBelongFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.*;

/**
 * roll手机归属地接口api调用
 * @author xiejs
 * @since 2022-01-18
 */
@FeignClient(name = "rollMobileBelong", url = ROLL_MOBILE_BELONG_URL, fallbackFactory = RollMobileBelongFeignFactory.class)
public interface RollMobileBelongFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_MOBILE_BELONG,
            url = ROLL_MOBILE_BELONG_URL,
            method = "Get")
    JSONObject mobileBelongApi(@SpringQueryMap RequestBody requestBody);
}
