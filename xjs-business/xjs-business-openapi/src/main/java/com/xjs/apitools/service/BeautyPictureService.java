package com.xjs.apitools.service;

import com.xjs.apitools.domain.ApiBeautyPicture;

import java.util.List;

/**
 * @author xiejs
 * @since 2022-07-02
 */
public interface BeautyPictureService  {
    /**
     * 获取随机的图片
     * @return list
     */
    List<ApiBeautyPicture> getRandomPicture();
}
