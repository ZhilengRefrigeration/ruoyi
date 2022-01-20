package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api简繁转换实体
 * @author xiejs
 * @since 2022-01-20
 */
@Data
public class ApiSimpleComplex implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原内容
     */
    private String originContent;

    /**
     * 转化后的内容
     */
    private String convertContent;

}
