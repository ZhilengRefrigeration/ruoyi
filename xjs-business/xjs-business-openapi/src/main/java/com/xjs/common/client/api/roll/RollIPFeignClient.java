package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.RollIPFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_IP;
import static com.xjs.consts.ApiConst.ROLL_IP_URL;

/**
 * rollip信息查询接口api调用
 *
 * @author xiejs
 * @since 2022-01-15
 */
@FeignClient(name = "rollIP", url = ROLL_IP_URL, fallbackFactory = RollIPFeignFactory.class)
public interface RollIPFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_IP,
            url = ROLL_IP_URL,
            method = "Get")
    JSONObject ipApi(@SpringQueryMap RequestBody requestBody);

}
