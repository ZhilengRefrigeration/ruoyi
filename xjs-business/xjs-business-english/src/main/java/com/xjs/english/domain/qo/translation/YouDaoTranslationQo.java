package com.xjs.english.domain.qo.translation;

import lombok.Data;

/**
 * @author xiejs
 * @desc 有道翻译条件实体
 * @create 2021-12-25
 */
@Data
public class YouDaoTranslationQo{

    /**
     * 响应类型
     */
    private String doctype="json";

    /**
     * 目标语言(有道的目标语言无效，中转英、英转中)
     */
    private String type;

    /**
     * 翻译的内容
     */
    private String i ;



}
