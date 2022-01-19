package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api mm图片实体
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Data
public class ApiBeautyPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 福利图片链接
     */
    private String imageUrl;


    /**
     * 福利图片尺寸
     */
    private String imageSize;


    /**
     * 福利图片文件大小
     */
    private String imageFileLength;
}
