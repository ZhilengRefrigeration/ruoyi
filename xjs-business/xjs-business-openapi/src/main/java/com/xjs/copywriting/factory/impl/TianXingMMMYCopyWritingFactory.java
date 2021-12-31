package com.xjs.copywriting.factory.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.client.TianXingMMMYFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.consts.TianXingConst;
import com.xjs.exception.ApiException;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-29
 */
@Service
public class TianXingMMMYCopyWritingFactory implements CopyWritingFactory {
    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingMMMYFeignClient tianXingMMMYeignClient;
    @Resource
    private CopyWritingMapper copyWritingMapper;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingMMMYeignClient.copyWritingApi(requestBody);
        if(jsonObject.containsKey("code")){
            if (HttpStatus.HTTP_OK !=jsonObject.getInteger("code")) {
                throw new ApiException("天行数据名人名言接口调用异常");
            }
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            String content = newslist.getJSONObject(0).getString("content");
            String author = newslist.getJSONObject(0).getString("author");
            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setContent(content);
            copyWriting.setSource(author);
            copyWriting.setType(TianXingConst.MMMY);
            copyWritingMapper.insert(copyWriting);
            return copyWriting;
        }else {
            //调用服务失败的降级之后的处理
            if (jsonObject.containsKey("error")) {
                return copyWritingMapper.getOneToRandom();
            }
            return new CopyWriting();
        }
    }
}
