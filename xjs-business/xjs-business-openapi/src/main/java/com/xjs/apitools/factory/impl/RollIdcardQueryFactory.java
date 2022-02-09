package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiIdcardQuery;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollIdcardQueryFeignClient;
import com.xjs.properties.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取身份证查询api工厂实现
 * @author xiejs
 * @since 2022-01-20
 */
@Component
@Log4j2
public class RollIdcardQueryFactory implements ApiToolsFactory<ApiIdcardQuery, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollIdcardQueryFeignClient rollIdcardQueryFeignClient;

    @Override
    public ApiIdcardQuery apiData(RequestBody req) {
        req.setApp_secret(rollProperties.getApp_secret());
        req.setApp_id(rollProperties.getApp_id());
        JSONObject jsonObject = rollIdcardQueryFeignClient.idcardQueryApi(req);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject data = jsonObject.getJSONObject("data");
            return data.toJavaObject(ApiIdcardQuery.class);
        }
        return null;
    }
}
