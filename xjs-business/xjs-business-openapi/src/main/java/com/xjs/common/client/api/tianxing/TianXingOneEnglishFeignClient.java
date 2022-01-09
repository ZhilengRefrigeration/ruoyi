package com.xjs.common.client.api.tianxing;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.TianXingOneEnglishFeignFactory;
import com.xjs.oneenglish.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_ONE_ENGLISH;
import static com.xjs.consts.ApiConst.TIANXING_ONE_ENGLISH_URL;

/**
 * @author xiejs
 * @desc  天行英语一言第三方api调用
 * @create 2021-12-31
 */
@FeignClient(name = "tianXingOneEnglish",url = TIANXING_ONE_ENGLISH_URL,fallbackFactory = TianXingOneEnglishFeignFactory.class)
public interface TianXingOneEnglishFeignClient {

    @GetMapping
    @ApiLog(name = TIANXING_ONE_ENGLISH,
            url = TIANXING_ONE_ENGLISH_URL,
            method = "Get")
    JSONObject oneEnglishApi(@SpringQueryMap RequestBody requestBody);
}
