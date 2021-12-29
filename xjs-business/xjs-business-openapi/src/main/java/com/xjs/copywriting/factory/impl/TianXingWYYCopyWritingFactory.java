package com.xjs.copywriting.factory.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.TianXingWYYFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.common.consts.TianXingConst;
import com.xjs.common.exception.ApiException;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc 天行数据网易云热评平台工厂实现
 * @create 2021-12-28
 */
@Service
public class TianXingWYYCopyWritingFactory implements CopyWritingFactory {

    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingWYYFeignClient tianXingWYYFeignClient;
    @Resource
    private CopyWritingMapper copyWritingMapper;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingWYYFeignClient.copyWritingApi(requestBody);
        //调用服务正常
        if(jsonObject.containsKey("code")){
            if (HttpStatus.HTTP_OK !=jsonObject.getInteger("code")) {
                throw new ApiException("天行数据网易云热评接口调用异常");
            }
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            String content = newslist.getJSONObject(0).getString("content");
            String source = newslist.getJSONObject(0).getString("source");
            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setContent(content);
            copyWriting.setSource(source);
            copyWriting.setType(TianXingConst.WYY);
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
