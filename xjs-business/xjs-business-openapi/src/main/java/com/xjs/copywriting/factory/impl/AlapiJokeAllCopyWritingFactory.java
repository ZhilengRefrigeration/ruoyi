package com.xjs.copywriting.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.alapi.AlapiJokeAllFeignClient;
import com.xjs.consts.CopyWritingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.exception.ApiException;
import com.xjs.properties.AlApiProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * alapi平台笑话大全 工厂实现
 *
 * @author xiejs
 * @since 2022-02-15
 */
@Log4j2
@Component
public class AlapiJokeAllCopyWritingFactory implements CopyWritingFactory {

    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private AlapiJokeAllFeignClient alapiJokeAllFeignClient;
    @Autowired
    private AlApiProperties alApiProperties;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        JSONObject jsonObject = alapiJokeAllFeignClient.alapiJokeAllApi(alApiProperties.getToken());
        if (jsonObject.containsKey(DEMOTE_ERROR)) {
            throw new ApiException("alapi平台笑话大全接口调用异常");
        }
        if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
            JSONObject dataJson = jsonObject.getJSONObject("data");
            String content = dataJson.getString("content");
            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setContent(content);
            copyWriting.setSource("笑话大全");
            copyWriting.setType(CopyWritingConst.XHDQ);
            copyWritingService.save(copyWriting);
            return copyWriting;
        } else {
            throw new ApiException("alapi平台笑话大全接口调用异常");
        }
    }
}
