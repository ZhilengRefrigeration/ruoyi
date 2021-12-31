package com.xjs.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.aop.ApiLog;
import com.xjs.client.factory.YouDaoFeignFactory;
import com.xjs.translation.domain.qo.translation.YouDaoTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.YOUDAO_FY;
import static com.xjs.consts.ApiConst.YOUDAO_FY_URL;

/**
 * @author xiejs
 * @desc  有道翻译接口api调用
 * @create 2021-12-25
 */
@FeignClient(name = "youdao",url = YOUDAO_FY_URL,fallbackFactory = YouDaoFeignFactory.class)
public interface YouDaoFeignClient {

    @GetMapping( headers ={ "Accept-Encoding=''"})
    @ApiLog(name = YOUDAO_FY,
            url = YOUDAO_FY_URL,
            method = "Get")
    JSONObject translationApi(@SpringQueryMap YouDaoTranslationQo qo);
}
