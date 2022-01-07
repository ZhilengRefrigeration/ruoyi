package com.xjs.translation.domain.qo.translation;

import lombok.Data;

/**
 * @author xiejs
 * @since 2022-01-07
 */
@Data
public class RollTranslationQo {
    /**
     * 翻译内容
     */
    private String content;

    /**
     * 翻译源语言
     */
    private String from = "auto";

    /**
     * 翻译目标语言
     */
    private String to ="auto";

    /**
     * 应用id
     */
    private String app_id;


    /**
     * 应用密钥
     */
    private String app_secret;
}
