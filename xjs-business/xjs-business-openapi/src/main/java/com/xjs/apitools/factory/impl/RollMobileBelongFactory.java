package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiMobileBelong;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollMobileBelongFeignClient;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取手机归属地api工厂实现
 * @author xiejs
 * @since 2022-01-18
 */
@Component
@Log4j2
public class RollMobileBelongFactory implements ApiToolsFactory<ApiMobileBelong, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollMobileBelongFeignClient rollMobileBelongFeignClient;


    @Override
    public ApiMobileBelong apiData(RequestBody req) {
        req.setApp_secret(rollProperties.getApp_secret());
        req.setApp_id(rollProperties.getApp_id());
        JSONObject jsonObject = rollMobileBelongFeignClient.mobileBelongApi(req);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            return jsonData.toJavaObject(ApiMobileBelong.class);
        }
        return null;
    }
}
