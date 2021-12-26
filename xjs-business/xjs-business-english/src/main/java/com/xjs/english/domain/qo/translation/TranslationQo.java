package com.xjs.english.domain.qo.translation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xiejs
 * @desc  翻译条件
 * @create 2021-12-25
 */
@Data
public class TranslationQo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 需要翻译的词(前端可以指定翻译词)
     */
    @NotBlank(message = "翻译内容不能为空")
    private String q="你傻吗，大傻逼？嗯，哈哈哈";

    /**
     * 翻译目标语言(前端可以指定目标语言)
     */
    private String to = "auto";

    /**
     * 翻译api类型(例如：有道、谷歌、百度等)
     * 1、百度 2、有道 3、谷歌 4 ...
     */
    @NotNull(message = "翻译api类型不能为空")
    private Integer translationType;
}
