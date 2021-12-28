package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.YouDaoFeignFactory;
import com.xjs.translation.domain.qo.translation.YouDaoTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejs
 * @desc  有道翻译接口api调用
 * @create 2021-12-25
 */
@FeignClient(name = "youdao",url = "http://fanyi.youdao.com/translate?",fallbackFactory = YouDaoFeignFactory.class)
public interface YouDaoFeignClient {

    @GetMapping( headers ={ "Accept-Encoding=''"})
    @ApiLog(name = "youdao",
            url = "http://fanyi.youdao.com/translate",
            method = "Get")
    JSONObject translationApi(@SpringQueryMap YouDaoTranslationQo qo);
}
