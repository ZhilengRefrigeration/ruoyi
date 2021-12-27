package com.xjs.translation.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.BaiduFeignClient;
import com.xjs.common.config.BaiduProperties;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.common.exception.BusinessException;
import com.xjs.translation.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@Service
public class BaiDuTranslationServiceImpl implements TranslationService {

    @Autowired
    private BaiduProperties baiduProperties;
    @Autowired
    private BaiduFeignClient baiduFeignClient;


    @Override
    public TranslationVo translationApi(TranslationQo translationQo) {
        String appId = baiduProperties.getAppId();
        BaiDuTranslationQo baiDuTranslationQo = new BaiDuTranslationQo();
        baiDuTranslationQo.setAppid(appId);
        String key = baiduProperties.getKey();
        //生成签名(appid+q+salt+密钥的MD5值)
        String append = appId + translationQo.getQ() + baiDuTranslationQo.getSalt() + key;
        String sign = SecureUtil.md5(append);
        baiDuTranslationQo.setSign(sign);
        baiDuTranslationQo.setQ(translationQo.getQ());
        JSONObject jsonObject = baiduFeignClient.translationApi(baiDuTranslationQo);
        if(Objects.nonNull(jsonObject.getString("error_code"))){
            throw new BusinessException("百度翻译接口调用异常");
        }
        TranslationVo translationVo = new TranslationVo();
        String from = jsonObject.getString("from");
        String to = jsonObject.getString("to");

        JSONArray transResult = jsonObject.getJSONArray("trans_result");
        HashMap<String, String> map = new HashMap<>();
        List<Map<String, String>> maps = new ArrayList<>();
        map.put("src", transResult.getJSONObject(0).getString("src"));
        map.put("dst", transResult.getJSONObject(0).getString("dst"));
        maps.add(map);
        translationVo.setFrom(from);
        translationVo.setTo(to);
        translationVo.setTransResult(maps);
        return translationVo;
    }
}
