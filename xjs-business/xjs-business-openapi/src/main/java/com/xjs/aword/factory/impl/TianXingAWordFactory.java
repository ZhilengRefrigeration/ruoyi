package com.xjs.aword.factory.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.aword.domain.ApiAWord;
import com.xjs.aword.domain.RequestBody;
import com.xjs.aword.factory.AWordFactory;
import com.xjs.aword.mapper.ApiAWordMapper;
import com.xjs.common.client.TianXingMMYJFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 天行数据平台每日一句工厂实现
 *
 * @author xiejs
 * @since 2022-01-08
 */
@Component
public class TianXingAWordFactory implements AWordFactory {
    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingMMYJFeignClient tianXingMMYJFeignClient;
    @Resource
    private ApiAWordMapper apiAWordMapper;

    @Override
    public ApiAWord productApiAWord(RequestBody requestBody) {
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingMMYJFeignClient.aWordApi(requestBody);
        if (jsonObject.containsKey("error")) {
            throw new ApiException("天行每日一句接口调用异常");
        }
        if (HttpStatus.HTTP_OK == jsonObject.getInteger("code")) {
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            JSONObject newslistJSONObject = newslist.getJSONObject(0);
            ApiAWord apiAWord = new ApiAWord();
            apiAWord.setContent(newslistJSONObject.getString("content"))
                    .setDataId(newslistJSONObject.getLong("id"))
                    .setSource(newslistJSONObject.getString("source"))
                    .setDate(newslistJSONObject.getDate("date"))
                    .setImgurl(newslistJSONObject.getString("imgurl"))
                    .setNote(newslistJSONObject.getString("note"))
                    .setTts(newslistJSONObject.getString("tts"));
            List<ApiAWord> apiAWordList = apiAWordMapper.selectList(new QueryWrapper<ApiAWord>()
                    .eq("data_id", apiAWord.getDataId()));
            if (CollUtil.isEmpty(apiAWordList)) {
                apiAWordMapper.insert(apiAWord);
            }
            return apiAWord;
        } else {
            return apiAWordMapper.selectOne(new QueryWrapper<ApiAWord>()
                    .orderByDesc("date")
                    .last("limit 1"));
        }
    }
}
