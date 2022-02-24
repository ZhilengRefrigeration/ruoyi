package com.xjs.common.client.api.baidu;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.BaiduTranslationFeignFactory;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static com.xjs.consts.ApiConst.*;

/**
 * 百度翻译接口api调用
 * @author xiejs
 * @since  2021-12-25
 */
@FeignClient(name = "baidu", url = BAIDU_FY_URL, fallbackFactory = BaiduTranslationFeignFactory.class)
public interface BaiduTranslationFeignClient {

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    @ApiLog(name = BAIDU_FY,
            url = BAIDU_FY_URL,
            method = "Post")
    JSONObject translationApi(BaiDuTranslationQo qo);

}
