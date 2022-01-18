package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollHolidayFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_HOLIDAYS;
import static com.xjs.consts.ApiConst.ROLL_HOLIDAYS_URL;

/**
 * roll节假日api接口feign远程调用
 * @author xiejs
 * @since 2022-01-18
 */
@FeignClient(name = "rollHoliday", url = ROLL_HOLIDAYS_URL, fallbackFactory = RollHolidayFeignFactory.class)
public interface RollHolidayFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_HOLIDAYS,
            url = ROLL_HOLIDAYS_URL,
            method = "Get")
    JSONObject holidayApi(@SpringQueryMap RequestBody requestBody);
}
