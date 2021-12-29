package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.BaiduFeignFactory;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static com.xjs.common.consts.ApiConst.*;

/**
 * @author xiejs
 * @desc 百度翻译接口api调用
 * @create 2021-12-25
 */
@FeignClient(name = "baidu", url = BAIDU_FY_URL, fallbackFactory = BaiduFeignFactory.class)
public interface BaiduFeignClient {

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    @ApiLog(name = BAIDU_FY,
            url = BAIDU_FY_URL,
            method = "Post")
    JSONObject translationApi(BaiDuTranslationQo qo);

}
