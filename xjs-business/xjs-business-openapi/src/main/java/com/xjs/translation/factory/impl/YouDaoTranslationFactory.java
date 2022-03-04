package com.xjs.translation.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.youdao.YouDaoFeignClient;
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

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 有道翻译工厂实现
 * @author xiejs
 * @since  2021-12-25
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
        if(!"0".equals(translationApi.getString("errorCode"))|| translationApi.containsKey(DEMOTE_ERROR) ){
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

        ArrayList<Map<String, String>> maps = new ArrayList<>();

        for (Object o : translateResult) {
            JSONArray jsonArrays = (JSONArray) o;
            JSONObject jsonObject = jsonArrays.getJSONObject(0);
            Map<String, String> map = new HashMap<String, String>();
            map.put("src", jsonObject.getString("src"));
            map.put("dst", jsonObject.getString("tgt"));
            maps.add(map);
        }

        translationVo.setTransResult(maps);
        return translationVo;
    }
}
