package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api手机归属地实体
 *
 * @author xiejs
 * @since 2022-01-18
 */
@Data
public class ApiMobileBelong implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 目标手机号
     */
    private String mobile;

    /**
     * 归属地省份
     */
    private String province;

    /**
     * 归属地描述
     */
    private String carrier;


}
