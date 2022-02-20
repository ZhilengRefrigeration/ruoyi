package com.xjs._36wallpaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs._36wallpaper.pojo._36wallpaper;

/**
 * 36壁纸网service接口
 * @author xiejs
 * @since 2022-02-20
 */
public interface _36wallpaperService extends IService<_36wallpaper> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
