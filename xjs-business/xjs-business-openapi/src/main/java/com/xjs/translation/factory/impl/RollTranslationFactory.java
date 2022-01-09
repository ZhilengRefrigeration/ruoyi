package com.xjs.translation.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.roll.RollTranslationFeignClient;
import com.xjs.config.RollProperties;
import com.xjs.exception.ApiException;
import com.xjs.translation.domain.qo.translation.RollTranslationQo;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * roll翻译平台工厂实现
 *
 * @author xiejs
 * @since 2022-01-07
 */
@Component
public class RollTranslationFactory implements TranslationFactory {
    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollTranslationFeignClient rollTranslationFeignClient;

    @Override
    public TranslationVo translationApi(TranslationQo translationQo) {
        RollTranslationQo rollTranslationQo = new RollTranslationQo();
        rollTranslationQo.setApp_id(rollProperties.getApp_id());
        rollTranslationQo.setApp_secret(rollProperties.getApp_secret());
        rollTranslationQo.setContent(translationQo.getQ());
        JSONObject translationApi = rollTranslationFeignClient.translationApi(rollTranslationQo);
        if (translationApi.containsKey("error")) {
            throw new ApiException("ROLL翻译接口调用异常");
        }
        if (translationApi.getInteger("code") == 1) {
            JSONObject data = translationApi.getJSONObject("data");
            TranslationVo translationVo = new TranslationVo();
            translationVo.setFrom(data.getString("originLanguage"));
            translationVo.setTo(data.getString("resultLanguage"));
            ArrayList<Map<String, String>> transResult = new ArrayList<>();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("src", data.getString("origin"));
            hashMap.put("dst", data.getString("result"));
            transResult.add(hashMap);
            translationVo.setTransResult(transResult);
            return translationVo;
        }else
        return new TranslationVo();
    }
}
