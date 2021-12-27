package com.xjs.translation.factory.impl;

import com.xjs.XjsEnglishApp;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@SpringBootTest(classes = XjsEnglishApp.class)
class YouDaoTranslationFactoryTest {

    @Resource(name = "youDaoTranslationFactory")
    TranslationFactory translationFactory;

    @Test
    void translationApi() {
        TranslationVo translationVo = translationFactory.translationApi(new TranslationQo());
        System.out.println(translationVo);
    }
}