package com.xjs._36wallpaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs._36wallpaper.mapper._36wallpaperMapper;
import com.xjs._36wallpaper.pojo._36wallpaper;
import com.xjs._36wallpaper.service._36wallpaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 36壁纸网service实现
 * @author xiejs
 * @since 2022-02-20
 */
@Service
public class _36wallpaperServiceImpl extends ServiceImpl<_36wallpaperMapper, _36wallpaper> implements _36wallpaperService {


    @Resource
    private _36wallpaperMapper wallpaperMapper;

    @Override
    public int deleteRepeatData() {
        return wallpaperMapper.deleteRepeatData();
    }
}
