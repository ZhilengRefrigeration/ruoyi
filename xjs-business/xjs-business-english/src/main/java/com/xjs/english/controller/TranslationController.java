package com.xjs.english.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.english.domain.qo.translation.TranslationQo;
import com.xjs.english.domain.vo.translation.TranslationVo;
import com.xjs.english.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.xjs.english.consts.TranslationTypeConst.BAIDU;
import static com.xjs.english.consts.TranslationTypeConst.YOUDAO;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@RestController
@RequestMapping("translation")
public class TranslationController {

    @Autowired
    private TranslationService youDaoTranslationServiceImpl;
    @Autowired
    private TranslationService baiDuTranslationServiceImpl;

    @PostMapping
    public AjaxResult translation(@Validated @RequestBody TranslationQo translationQo) {
        TranslationVo translationVo=new TranslationVo();
        if (BAIDU.equals(translationQo.getTranslationType())) {
            translationVo = baiDuTranslationServiceImpl.translationApi(translationQo);
        }
        if (YOUDAO.equals(translationQo.getTranslationType())) {
            translationVo = youDaoTranslationServiceImpl.translationApi(translationQo);
        }
        return AjaxResult.success(translationVo);
    }
}
