package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiBeautyPicture;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollBeautyPictureFeignClient;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取mm图片api工厂实现
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Component
@Log4j2
public class RollBeautyPictureFactory implements ApiToolsFactory<ApiBeautyPicture, Object> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollBeautyPictureFeignClient rollBeautyPictureFeignClient;


    @Override
    public List<ApiBeautyPicture> apiDataList() {
        RequestBody requestBody = new RequestBody();
        requestBody.setApp_secret(rollProperties.getApp_secret());
        requestBody.setApp_id(rollProperties.getApp_id());
        JSONObject jsonObject = rollBeautyPictureFeignClient.beautyPictureApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            return jsonArrayData.stream().map(data -> {
                JSONObject jsonData = (JSONObject) data;
                return jsonData.toJavaObject(ApiBeautyPicture.class);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
