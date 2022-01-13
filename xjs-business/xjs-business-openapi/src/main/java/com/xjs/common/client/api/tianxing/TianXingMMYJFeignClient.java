package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.aword.domain.RequestBody;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.TianXingMMYJFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_A_WORD;
import static com.xjs.consts.ApiConst.TIANXING_A_WORD_URL;

/**
 * 天行每日一句feign远程api调用
 * @author xiejs
 * @since 2022-01-08
 */
@FeignClient(name = "tianXingMMYJ", url = TIANXING_A_WORD_URL, fallbackFactory = TianXingMMYJFeignFactory.class)
public interface TianXingMMYJFeignClient {


    @GetMapping
    @ApiLog(name = TIANXING_A_WORD,
            url = TIANXING_A_WORD_URL,
            method = "Get")
    JSONObject aWordApi(@SpringQueryMap RequestBody requestBody);


}
