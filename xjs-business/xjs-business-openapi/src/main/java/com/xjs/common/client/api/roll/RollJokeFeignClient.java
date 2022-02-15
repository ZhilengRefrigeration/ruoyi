package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.RollJokeFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_JOKE;
import static com.xjs.consts.ApiConst.ROLL_JOKE_URL;


/**
 * roll平台 搞笑段子 api feign
 * @author xiejs
 * @since 2022-02-15
 */
@FeignClient(name = "rollJoke", url = ROLL_JOKE_URL, fallbackFactory = RollJokeFeignFactory.class)
public interface RollJokeFeignClient {
    @GetMapping()
    @ApiLog(name = ROLL_JOKE,
            url = ROLL_JOKE_URL,
            method = "Get")
    JSONObject jokeApi(@SpringQueryMap RequestBody requestBody);
}
