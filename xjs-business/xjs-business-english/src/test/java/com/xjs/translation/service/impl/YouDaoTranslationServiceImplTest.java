package com.xjs.translation.service.impl;

import com.xjs.translation.XjsEnglishApp;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.service.TranslationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@SpringBootTest(classes = XjsEnglishApp.class)
class YouDaoTranslationServiceImplTest {

    @Resource(name = "youDaoTranslationServiceImpl")
    TranslationService translationService;

    @Test
    void translationApi() {
        TranslationVo translationVo = translationService.translationApi(new TranslationQo());
        System.out.println(translationVo);
    }
}