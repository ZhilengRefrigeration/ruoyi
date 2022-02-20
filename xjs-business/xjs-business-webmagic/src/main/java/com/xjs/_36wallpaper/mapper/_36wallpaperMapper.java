package com.xjs._36wallpaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs._36wallpaper.pojo._36wallpaper;

/**
 * 36壁纸网mapper
 * @author xiejs
 * @since 2022-02-20
 */
public interface _36wallpaperMapper extends BaseMapper<_36wallpaper> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
