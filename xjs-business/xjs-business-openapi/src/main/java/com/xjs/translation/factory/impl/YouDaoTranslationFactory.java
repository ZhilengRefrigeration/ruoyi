package com.xjs.translation.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.client.YouDaoFeignClient;
import com.xjs.exception.ApiException;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.qo.translation.YouDaoTranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@Service
public class YouDaoTranslationFactory implements TranslationFactory {

    @Autowired
    private YouDaoFeignClient youDaoFeignClient;

    @Override
    public TranslationVo translationApi(TranslationQo translationQo) {
        YouDaoTranslationQo youDaoTranslationQo = new YouDaoTranslationQo();
        youDaoTranslationQo.setI(translationQo.getQ());
        JSONObject translationApi = youDaoFeignClient.translationApi(youDaoTranslationQo);
        //接口内部错误以及网络错误都抛异常
        if(!"0".equals(translationApi.getString("errorCode"))|| translationApi.containsKey("error") ){
            if(!"40".equals(translationApi.getString("errorCode"))){
                throw new ApiException("有道翻译接口调用异常");
            }
        }
        String type = translationApi.getString("type");
        TranslationVo translationVo = new TranslationVo();
        translationVo.setType(type);
        Long errorCode =Long.parseLong(translationApi.getString("errorCode"));
        translationVo.setErrorCode(errorCode);
        long elapsedTime = Long.parseLong(translationApi.getString("elapsedTime"));
        translationVo.setElapsedTime(elapsedTime);
        JSONArray translateResult = translationApi.getJSONArray("translateResult");
        JSONArray jsonArray = translateResult.getJSONArray(0);
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("src", jsonArray.getJSONObject(i).getString("src"));
                map.put("dst", jsonArray.getJSONObject(i).getString("tgt"));
                maps.add(map);
            }
        }
        translationVo.setTransResult(maps);
        return translationVo;
    }
}
