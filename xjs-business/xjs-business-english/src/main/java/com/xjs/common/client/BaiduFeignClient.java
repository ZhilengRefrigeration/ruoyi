package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiejs
 * @desc  百度翻译接口api调用
 * @create 2021-12-25
 */
@FeignClient(name = "baidu",url = "http://api.fanyi.baidu.com/api/trans/vip/translate?")
public interface BaiduFeignClient {

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    @ApiLog(name = "baidu",
            url = "http://api.fanyi.baidu.com/api/trans/vip/translate",
            method = "Post")
    JSONObject translationApi(BaiDuTranslationQo qo);

}
