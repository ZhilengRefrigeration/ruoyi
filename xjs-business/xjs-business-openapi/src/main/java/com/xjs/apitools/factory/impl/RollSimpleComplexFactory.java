package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiSimpleComplex;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollSimpleComplexFeignClient;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * @author xiejs
 * @since 2022-01-20
 */
@Component
@Log4j2
public class RollSimpleComplexFactory implements ApiToolsFactory<ApiSimpleComplex, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollSimpleComplexFeignClient rollSimpleComplexFeignClient;

    @Override
    public ApiSimpleComplex apiData(RequestBody req) {
        req.setApp_secret(rollProperties.getApp_secret());
        req.setApp_id(rollProperties.getApp_id());
        //type  1 简体转繁体 2 繁体转简体
        req.setType(1);
        JSONObject jsonObject = rollSimpleComplexFeignClient.simpleComplexApi(req);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            return jsonData.toJavaObject(ApiSimpleComplex.class);
        }
        return null;
    }
}
