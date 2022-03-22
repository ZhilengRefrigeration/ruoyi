package com.xjs.copywriting.factory.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.tianxing.TianXingPYQFeignClient;
import com.xjs.consts.CopyWritingConst;
import com.xjs.properties.TianXingProperties;
import com.xjs.exception.ApiException;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行数据朋友圈文案平台工厂实现
 * @author xiejs
 * @since  2021-12-27
 */
@Service
public class TianXingPYQCopyWritingFactory implements CopyWritingFactory {

    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingPYQFeignClient tianXingPYQFeignClient;
    @Resource
    private CopyWritingMapper copyWritingMapper;

    @Override
    @Transactional
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingPYQFeignClient.copyWritingApi(requestBody);
        //调用服务正常
        if(jsonObject.containsKey("code")){
            if (HttpStatus.HTTP_OK !=jsonObject.getInteger("code")) {
                throw new ApiException("天行数据朋友圈文案接口调用异常");
            }
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            String content = newslist.getJSONObject(0).getString("content");
            String source = newslist.getJSONObject(0).getString("source");
            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setContent(content);
            copyWriting.setSource(source);
            copyWriting.setType(CopyWritingConst.PYQ);
            copyWritingMapper.insert(copyWriting);
            return copyWriting;
        }else {
            //调用服务失败的降级之后的处理
            if (jsonObject.containsKey(DEMOTE_ERROR)) {
                return copyWritingMapper.getOneToRandom();
            }
            return new CopyWriting();
        }
    }
}