package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiHistoryToday;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollHistoryTodayFeignClient;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取历史今天api工厂实现
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Component
@Log4j2
public class RollHistoryTodayFactory implements ApiToolsFactory<ApiHistoryToday, Object> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollHistoryTodayFeignClient rollHistoryTodayFeignClient;

    @Override
    public List<ApiHistoryToday> apiDataList() {
        RequestBody requestBody = new RequestBody();
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        //获取无详情的数据
        requestBody.setType(0);
        JSONObject jsonObject = rollHistoryTodayFeignClient.historyTodayApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            return jsonArrayData.stream().map(data -> {
                JSONObject jsonData = (JSONObject) data;
                return jsonData.toJavaObject(ApiHistoryToday.class);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
