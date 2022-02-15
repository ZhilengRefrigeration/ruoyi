package com.xjs.copywriting.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.roll.RollJokeFeignClient;
import com.xjs.consts.CopyWritingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.exception.ApiException;
import com.xjs.properties.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.ROLL_CODE_SUCCESS;

/**
 * roll平台 搞笑段子 工厂实现
 * @author xiejs
 * @since 2022-02-15
 */
@Component
@Log4j2
public class RollJokeCopyWritingFactory implements CopyWritingFactory {

    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollJokeFeignClient rollJokeFeignClient;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollJokeFeignClient.jokeApi(requestBody);
        if (jsonObject.containsKey(DEMOTE_ERROR)) {
            throw new ApiException("roll搞笑段子接口调用异常");
        }
        if (ROLL_CODE_SUCCESS.equals(jsonObject.getInteger("code"))) {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            List<CopyWriting> collect = jsonArrayData.stream().map(json -> {
                CopyWriting copyWriting = new CopyWriting();
                JSONObject toJSON = (JSONObject) JSONObject.toJSON(json);
                copyWriting.setSource("搞笑段子");
                copyWriting.setContent(toJSON.getString("content"));
                copyWriting.setType(CopyWritingConst.GXDZ);
                return copyWriting;
            }).collect(Collectors.toList());
            log.info("搞笑段子批量插入成功了嘛---"+copyWritingService.saveBatch(collect));
            return collect.get(0);
        }else {
            throw new ApiException("roll搞笑段子接口调用异常");
        }
    }
}
