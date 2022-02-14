package com.xjs.copywriting.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.uomg.UomgEarthLoveFeignClient;
import com.xjs.consts.CopyWritingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * Uomg平台 土味情话 api工厂实现
 * @author xiejs
 * @since 2022-02-14
 */
@Service
@Deprecated
public class UomgEarthLoveCopyWritingFactory implements CopyWritingFactory {
    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private UomgEarthLoveFeignClient uomgEarthLoveFeignClient;


    @Override
    @Deprecated
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setFormat("json");
        JSONObject jsonObject = uomgEarthLoveFeignClient.earthLoveApi(requestBody);
        if (jsonObject.containsKey(DEMOTE_ERROR)) {
            throw new ApiException("Uomg平台 土味情话接口调用异常");
        }
        String content = jsonObject.getString("content");
        CopyWriting copyWriting = new CopyWriting();
        copyWriting.setContent(content);
        copyWriting.setSource("土味情话");
        copyWriting.setType(CopyWritingConst.TWQH);
        copyWritingService.save(copyWriting);
        return copyWriting;
    }
}
