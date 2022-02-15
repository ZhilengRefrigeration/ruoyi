package com.xjs.copywriting.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.roll.RollMMYJFeignClient;
import com.xjs.properties.RollProperties;
import com.xjs.consts.CopyWritingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * roll平台每日一句工厂实现
 * @author xiejs
 * @since 2022-01-08
 */
@Component
@Log4j2
public class RollMMYJCopyWritingFactory implements CopyWritingFactory {
    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private RollMMYJFeignClient rollMMYJFeignClient;
    @Autowired
    private CopyWritingService copyWritingService;

    @Override
    @Transactional
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setCount(20);
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollMMYJFeignClient.copyWritingApi(requestBody);
        if (jsonObject.containsKey(DEMOTE_ERROR)) {
            throw new ApiException("roll每日一句接口调用异常");
        }
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<CopyWriting> collect = jsonArray.stream().map(json -> {
            CopyWriting copyWriting = new CopyWriting();
            JSONObject toJSON = (JSONObject) JSONObject.toJSON(json);
            copyWriting.setContent(toJSON.getString("content"));
            copyWriting.setSource(toJSON.getString("author"));
            copyWriting.setType(CopyWritingConst.MRYJ);
            if (StringUtils.isBlank(copyWriting.getSource())) {
                copyWriting.setSource("匿名");
            }
            return copyWriting;
        }).collect(Collectors.toList());
        log.info("每日一句批量插入成功了嘛---"+copyWritingService.saveBatch(collect));
        return collect.get(0);
    }
}
