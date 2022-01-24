package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiGarbageSorting;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollGarbageSortingDeignClient;
import com.xjs.config.RollProperties;
import com.xjs.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.*;

/**
 * roll平台获取垃圾分类api工厂实现
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Component
@Log4j2
public class RollGarbageSortingFactory implements ApiToolsFactory<ApiGarbageSorting, RequestBody> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollGarbageSortingDeignClient rollGarbageSortingDeignClient;


    @Override
    public ApiGarbageSorting apiData(RequestBody req) {
        req.setApp_secret(rollProperties.getApp_secret());
        req.setApp_id(rollProperties.getApp_id());
        JSONObject jsonObject = rollGarbageSortingDeignClient.garbageSortingApi(req);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            return jsonData.toJavaObject(ApiGarbageSorting.class);
        } else if (jsonObject.containsKey("code") && jsonObject.getInteger("code") == ROLL_CODE_ERROR.intValue()) {
            throw new ApiException("未搜索到相关物品垃圾分类信息，该关键词已上报，感谢您的使用");
        }
        return null;
    }
}
