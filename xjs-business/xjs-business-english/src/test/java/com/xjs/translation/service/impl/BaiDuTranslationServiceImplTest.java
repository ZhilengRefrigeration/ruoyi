package com.xjs.translation.service.impl;

import com.xjs.translation.XjsEnglishApp;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.service.TranslationService;
import org.springframework.boot.test.context.SpringBootTest;

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

    @org.junit.jupiter.api.Test
    void handlerTranslationApi() {
        TranslationVo translationVo = translationService.translationApi(new TranslationQo());
        System.out.println(translationVo);
    }
}