package com.xjs.common.client.api.time;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.TimeFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.TIME;
import static com.xjs.consts.ApiConst.TIME_URL;

/**
 * 网络时间feign
 * @author xiejs
 * @since 2022-02-26
 */
@FeignClient(name = "timeFeign", url = TIME_URL, fallbackFactory = TimeFeignFactory.class)
public interface TimeFeignClient {

    @GetMapping
    @ApiLog(name = TIME,
            url = TIME_URL,
            method = "Get")
    JSONObject timeApi(@RequestParam(value = "ttd_pid",defaultValue = "pubmatic")String ttd_pid,
                       @RequestParam(value = "fmt",defaultValue = "json")String fmt);

}
