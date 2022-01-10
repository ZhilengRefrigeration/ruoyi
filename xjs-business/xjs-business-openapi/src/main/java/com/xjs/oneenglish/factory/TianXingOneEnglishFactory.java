package com.xjs.oneenglish.factory;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.common.client.api.tianxing.TianXingOneEnglishFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.exception.ApiException;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import com.xjs.oneenglish.mapper.ApiEnglishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.ONE_ENGLISH;
import static com.xjs.consts.RedisConst.ONE_ENGLISH_EXPIRE;

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
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public ApiEnglish getOneEnglish(RequestBody requestBody) {
        ApiEnglish apiEnglish = new ApiEnglish();
        //redis中有值直接返回
        if (redisService.hasKey(ONE_ENGLISH)) {
            Map<String, String> apiEnglishMap = redisService.getCacheMap(ONE_ENGLISH);
            apiEnglish.setEn(apiEnglishMap.get("en"));
            apiEnglish.setZh(apiEnglishMap.get("zh"));
            return apiEnglish;
        }
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingOneEnglishFeignClient.oneEnglishApi(requestBody);
        if (!jsonObject.containsKey("error")) {
            if (jsonObject.getInteger("code") == HttpStatus.HTTP_OK) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                JSONObject content = newslist.getJSONObject(0);
                apiEnglish.setEn(content.getString("en"));
                apiEnglish.setZh(content.getString("zh"));
                apiEnglishMapper.insert(apiEnglish);
                //把数据存入redis
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("en", apiEnglish.getEn());
                hashMap.put("zh", apiEnglish.getZh());
                redisService.setCacheMap(ONE_ENGLISH,hashMap);
                //设置过期时间
                redisService.expire(ONE_ENGLISH, ONE_ENGLISH_EXPIRE, TimeUnit.MINUTES);
            }else {
                throw new ApiException("英语一言接口调用成功，但内部错误");
            }
        } else {
            throw new ApiException("英语一言接口降级！！！");
        }
        return apiEnglish;
    }
}
