package com.xjs.english.service.impl;

import com.xjs.english.XjsEnglishApp;
import com.xjs.english.domain.qo.translation.BaiDuTranslationQo;
import com.xjs.english.domain.qo.translation.TranslationQo;
import com.xjs.english.domain.vo.translation.TranslationVo;
import com.xjs.english.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

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