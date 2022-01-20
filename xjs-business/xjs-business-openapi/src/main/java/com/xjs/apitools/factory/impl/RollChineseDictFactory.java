package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiChineseDict;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollChineseDictFeignClient;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取汉语字典api工厂实现
 * @author xiejs
 * @since 2022-01-20
 */
@Component
@Log4j2
public class RollChineseDictFactory implements ApiToolsFactory<ApiChineseDict, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollChineseDictFeignClient rollChineseDictFeignClient;



    @Override
    public ApiChineseDict apiData(RequestBody req) {
        req.setApp_id(rollProperties.getApp_id());
        req.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollChineseDictFeignClient.chineseDictApi(req);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            JSONObject jsonData = jsonArrayData.getJSONObject(0);
            return jsonData.toJavaObject(ApiChineseDict.class);
        }
        return null;
    }
}
