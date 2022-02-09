package com.xjs.translation.factory.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.tianxing.TianXingTranDictClient;
import com.xjs.properties.TianXingProperties;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.exception.ApiException;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * @author xiejs
 * @desc 天行数据平台翻译字典实现工厂
 * @create 2021-12-30
 */
@Component
public class TianXingTranDictFactory implements TranslationFactory {
    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingTranDictClient tianXingTranDictClient;

    @Override
    public TranslationVo translationApi(TranslationQo translationQo) {
        RequestBody requestBody = new RequestBody();
        requestBody.setWord(translationQo.getQ());
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingTranDictClient.tranDictApi(requestBody);
        TranslationVo translationVo = new TranslationVo();
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            //代表没进入降级类
            if (jsonObject.getInteger("code") == 250) {
                throw new ApiException("内容输入错误");
            }
            if (jsonObject.getInteger("code") == HttpStatus.HTTP_OK) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                JSONObject contentJson = newslist.getJSONObject(0);
                String content = contentJson.getString("content");
                translationVo.setTo(content);
            } else {
                throw new ApiException("api调用成功，但返回错误信息");
            }
        }else {
            translationVo.setTo("  -  ");
        }
        return translationVo;
    }
}
