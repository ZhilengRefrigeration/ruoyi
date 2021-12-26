package com.xjs.english.client;

import com.xjs.english.domain.qo.translation.BaiDuTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@FeignClient(name = "baidu",url = "http://api.fanyi.baidu.com/api/trans/vip/translate?")
public interface BaiduFeignClient {

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    String translationApi(BaiDuTranslationQo qo);

}
