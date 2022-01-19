package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollGarbageSortingFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_GARBAGE_SORTING;
import static com.xjs.consts.ApiConst.ROLL_GARBAGE_SORTING_URL;


/**
 * roll 垃圾分类api接口feign远程调用
 * @author xiejs
 * @since 2022-01-19
 */
@FeignClient(name = "rollGarbageSorting", url = ROLL_GARBAGE_SORTING_URL, fallbackFactory = RollGarbageSortingFeignFactory.class)
public interface RollGarbageSortingDeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_GARBAGE_SORTING,
            url = ROLL_GARBAGE_SORTING_URL,
            method = "Get")
    JSONObject garbageSortingApi(@SpringQueryMap RequestBody requestBody);

}
