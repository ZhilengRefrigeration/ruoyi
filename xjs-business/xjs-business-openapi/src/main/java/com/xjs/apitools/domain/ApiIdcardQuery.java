package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api身份证查询
 * @author xiejs
 * @since 2022-01-20
 */
@Data
public class ApiIdcardQuery implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 身份证号码
     */
    private String idCardNum;


    /**
     * 身份证所属归属地
     */
    private String address;


    /**
     * 生日
     */
    private String birthday;


    /**
     * 性别
     */
    private String sex;

}
