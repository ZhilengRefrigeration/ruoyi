package com.xjs.translation.domain.vo.translation;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 翻译实体类VO
 *
 * @author xiejs
 * @since 2021-12-25
 */
@Data
public class TranslationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 源语言
     */
    private String from;

    /**
     * 目标语言
     */
    private String to;

    /**
     * 翻译结果
     */
    private List<Map<String, String>> transResult;

    /**
     * 是否错误
     */
    private Long errorCode;


    /**
     * 运行时间，单位毫秒
     */
    private Long elapsedTime;


    /**
     * 语言类型
     */
    private String type;


}
