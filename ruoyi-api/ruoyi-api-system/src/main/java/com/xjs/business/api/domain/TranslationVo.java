package com.xjs.business.api.domain;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiejs
 * @desc  翻译实体类VO
 * @create 2021-12-25
 */
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Map<String, String>> getTransResult() {
        return transResult;
    }

    public void setTransResult(List<Map<String, String>> transResult) {
        this.transResult = transResult;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
