package com.xjs.oneenglish.factory;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.TianXingOneEnglishFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.exception.ApiException;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import com.xjs.oneenglish.mapper.ApiEnglishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc 一言英语工厂实现
 * @create 2021-12-31
 */
@Component
public class TianXingOneEnglishFactory implements OneEnglishFactory {
    @Resource
    private ApiEnglishMapper apiEnglishMapper;

    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingOneEnglishFeignClient tianXingOneEnglishFeignClient;

    @Override
    public ApiEnglish getOneEnglish(RequestBody requestBody) {
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingOneEnglishFeignClient.oneEnglishApi(requestBody);
        ApiEnglish apiEnglish = new ApiEnglish();
        if (!jsonObject.containsKey("error")) {
            if (jsonObject.getInteger("code") == HttpStatus.HTTP_OK) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                JSONObject content = newslist.getJSONObject(0);
                apiEnglish.setEn(content.getString("en"));
                apiEnglish.setZh(content.getString("zh"));
                apiEnglishMapper.insert(apiEnglish);
            }else {
                throw new ApiException("英语一言接口调用成功，但内部错误");
            }
        } else {
            throw new ApiException("英语一言接口降级！！！");
        }
        return apiEnglish;
    }
}
