package com.xjs.apitools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.apitools.domain.ApiBeautyPicture;

import java.util.List;

/**
 * @author xiejs
 * @since 2022-07-02
 */
public interface BeautyPictureMapper extends BaseMapper<ApiBeautyPicture> {

    List<ApiBeautyPicture> getRandomPicture();

}
