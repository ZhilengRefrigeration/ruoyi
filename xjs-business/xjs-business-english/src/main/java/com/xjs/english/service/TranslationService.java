package com.xjs.english.service;

import com.xjs.english.domain.qo.translation.TranslationQo;
import com.xjs.english.domain.vo.translation.TranslationVo;

/**
 * @author xiejs
 * @desc  翻译统一调用接口
 * @create 2021-12-25
 */
public interface TranslationService {

    /**
     * 调用百度翻译接口
     * @param translationQo 翻译条件封装
     * @return 翻译结果封装
     */
    TranslationVo translationApi(TranslationQo translationQo);



}
