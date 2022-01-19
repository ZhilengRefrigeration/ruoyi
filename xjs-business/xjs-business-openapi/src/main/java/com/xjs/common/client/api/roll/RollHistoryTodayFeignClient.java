package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollHistoryTodayFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_HISTORY_TODAY;
import static com.xjs.consts.ApiConst.ROLL_HISTORY_TODAY_URL;

/**
 * roll历史今天api接口feign远程调用
 * @author xiejs
 * @since 2022-01-19
 */
@FeignClient(name = "rollHistoryToday", url = ROLL_HISTORY_TODAY_URL, fallbackFactory = RollHistoryTodayFeignFactory.class)
public interface RollHistoryTodayFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_HISTORY_TODAY,
            url = ROLL_HISTORY_TODAY_URL,
            method = "Get")
    JSONObject historyTodayApi(@SpringQueryMap RequestBody requestBody);
}
