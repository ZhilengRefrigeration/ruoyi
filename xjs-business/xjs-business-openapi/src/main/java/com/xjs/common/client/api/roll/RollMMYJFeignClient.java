package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.RollMMYJFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_A_WORD;
import static com.xjs.consts.ApiConst.ROLL_A_WORD_URL;

/**
 * roll每日一句接口api调用
 * @author xiejs
 * @since 2022-01-08
 */
@FeignClient(name = "rollMMYJ", url = ROLL_A_WORD_URL, fallbackFactory = RollMMYJFeignFactory.class)
public interface RollMMYJFeignClient {
    @GetMapping
    @ApiLog(name = ROLL_A_WORD,
            url = ROLL_A_WORD_URL,
            method = "Get")
    JSONObject copyWritingApi(@SpringQueryMap RequestBody requestBody);
}
