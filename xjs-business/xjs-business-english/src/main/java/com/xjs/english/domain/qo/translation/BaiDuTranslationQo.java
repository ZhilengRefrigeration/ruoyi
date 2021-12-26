package com.xjs.english.domain.qo.translation;

import lombok.Data;

/**
 * @author xiejs
 * @desc 百度翻译条件
 * @create 2021-12-25
 */
@Data
public class BaiDuTranslationQo {
    /**
     * appid
     */
    private String appid;

    /**
     * 密钥
     */
    private String key;

    /**
     * 需要翻译的词
     */
    private String q;

    /**
     * 翻译目标语言
     */
    private String to ;


    /**
     * 翻译源语言
     */
    private String from = "auto";


    /**
     * 盐(随机数)
     */
    private String salt = "xjsisyourfatter";

    /**
     * 签名（appid+query+salt+key的MD5值）
     */
    private String sign;
}
