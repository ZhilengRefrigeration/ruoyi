package com.xjs.english.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.english.domain.qo.translation.YouDaoTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@FeignClient(name = "youdao",url = "http://fanyi.youdao.com/translate?")
public interface YouDaoFeignClient {

    @GetMapping( headers ={ "Accept-Encoding=''"})
    JSONObject translationApi(@SpringQueryMap YouDaoTranslationQo qo);
}
