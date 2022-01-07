package com.xjs.common.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.aop.ApiLog;
import com.xjs.common.client.factory.RollTranslationFeignFactory;
import com.xjs.translation.domain.qo.translation.RollTranslationQo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_FY;
import static com.xjs.consts.ApiConst.ROLL_FY_URL;

/**
 * roll翻译接口api远程调用feign
 * @author xiejs
 * @since 2022-01-07
 */
@FeignClient(name = "rollTranslation", url = ROLL_FY_URL, fallbackFactory = RollTranslationFeignFactory.class)
public interface RollTranslationFeignClient {

    @GetMapping
    @ApiLog(name = ROLL_FY,
            url = ROLL_FY_URL,
            method = "Get")
    JSONObject translationApi(@SpringQueryMap RollTranslationQo qo);
}
