package com.xjs.apitools.service.impl;

import com.xjs.apitools.domain.ApiBeautyPicture;
import com.xjs.apitools.mapper.BeautyPictureMapper;
import com.xjs.apitools.service.BeautyPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiejs
 * @since 2022-07-02
 */
@Service
public class BeautyPictureServiceImpl implements BeautyPictureService {

    @Resource
    private BeautyPictureMapper beautyPictureMapper;

    @Override
    public List<ApiBeautyPicture> getRandomPicture() {
        List<ApiBeautyPicture> beautyPictureList = beautyPictureMapper.getRandomPicture();

        return beautyPictureList;
    }
}
