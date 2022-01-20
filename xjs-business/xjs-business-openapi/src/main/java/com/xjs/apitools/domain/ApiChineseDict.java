package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api汉语字典实体
 * @author xiejs
 * @since 2022-01-20
 */
@Data
public class ApiChineseDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原内容
     */
    private String word;

    /**
     * 繁体
     */
    private String traditional;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 偏旁部首
     */
    private String radicals;

    /**
     * 汉字释义
     */
    private String explanation;

    /**
     * 汉字笔画数
     */
    private String strokes;

}
