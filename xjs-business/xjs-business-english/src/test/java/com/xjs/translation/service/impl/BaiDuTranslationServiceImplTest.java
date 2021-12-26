package com.xjs.translation.service.impl;

import com.xjs.XjsEnglishApp;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.service.TranslationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@SpringBootTest(classes = XjsEnglishApp.class)
class BaiDuTranslationServiceImplTest {
    @Resource(name = "baiDuTranslationServiceImpl")
    TranslationService translationService;

    @Autowired
    RedisTemplate redisTemplate;
    @org.junit.jupiter.api.Test
    void handlerTranslationApi() {
        TranslationVo translationVo = translationService.translationApi(new TranslationQo());
        System.out.println(translationVo);
    }

    @Test
    public void redis() {
        Object o = redisTemplate.opsForValue().get("login_tokens:043f6e6c-fc10-4f6e-8cd4-1243cdb2fa09");
        System.out.println(o);
    }
}