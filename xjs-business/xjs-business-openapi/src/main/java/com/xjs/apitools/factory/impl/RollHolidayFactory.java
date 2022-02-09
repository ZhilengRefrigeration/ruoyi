package com.xjs.apitools.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.apitools.domain.ApiHoliday;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.common.client.api.roll.RollHolidayFeignClient;
import com.xjs.properties.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台获取节假日api工厂实现
 *
 * @author xiejs
 * @since 2022-01-17
 */
@Component
@Log4j2
public class RollHolidayFactory implements ApiToolsFactory<ApiHoliday, Object> {

    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollHolidayFeignClient rollHolidayFeignClient;


    @Override
    public List<ApiHoliday> apiDataList() {
        RequestBody requestBody = new RequestBody();
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollHolidayFeignClient.holidayApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR) && jsonObject.getInteger("code") == ROLL_CODE_SUCCESS.intValue()) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            return jsonArrayData.stream().map(data -> {
                JSONObject jsonData = (JSONObject) data;
                return jsonData.toJavaObject(ApiHoliday.class);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
