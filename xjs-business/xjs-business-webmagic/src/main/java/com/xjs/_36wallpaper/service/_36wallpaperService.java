package com.xjs._36wallpaper.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs._36wallpaper.pojo._36wallpaper;

import java.util.List;

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


    /**
     * 从配置管理中获取指定的参数配置
     * @return json
     */
    JSONObject getSettings();

    /**
     * 修改配置参数
     * @param json json
     * @return boolean
     */
    boolean updateSettings(String json);

    /**
     * 重置配置参数
     * @return boolean
     */
    boolean resetSettings();

    /**
     * 分页查询壁纸列表
     * @param wallpaper 实体类
     * @return page
     */
    IPage<_36wallpaper> selectWallpaperList(Page<_36wallpaper> page, _36wallpaper wallpaper);


    /**
     * 获取壁纸类别
     * @return list
     */
    List<Object> getType();

}
