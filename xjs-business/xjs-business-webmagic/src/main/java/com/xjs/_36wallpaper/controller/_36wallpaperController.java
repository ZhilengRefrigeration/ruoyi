package com.xjs._36wallpaper.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs._36wallpaper.service._36wallpaperService;
import com.xjs._36wallpaper.task._36wallpaperTask;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 36壁纸网爬虫controller
 *
 * @author xiejs
 * @since 2022-02-20
 */
@RestController
@RequestMapping("_36wallpaper")
@Api(tags = "爬虫模块-36壁纸网")
public class _36wallpaperController extends MyBaseController {

    @Autowired
    private _36wallpaperTask wallpaperTask;

    @Autowired
    private _36wallpaperService wallpaperService;


    @GetMapping("getSettings")
    @ApiOperation("获取参数配置")
    public AjaxResult getSettings() {
        JSONObject jsonObject = wallpaperService.getSettings();
        if (Objects.nonNull(jsonObject)) {
            return AjaxResult.success(jsonObject);
        }
        return AjaxResult.error();
    }


    @PutMapping("updateSettings")
    @ApiOperation("修改参数配置")
    public AjaxResult updateSettings(@RequestParam("json") String json) {
        boolean b=wallpaperService.updateSettings(json);
        return toAjax(b);
    }

    @PutMapping("reset")
    @ApiOperation("重置参数配置")
    public AjaxResult resetSettings() {
        boolean b=wallpaperService.resetSettings();
        return toAjax(b);
    }


    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R _36wallpaperControllerTaskForPRC() {
        Long count = wallpaperTask.reptileWallpaper();
        return R.ok(count);
    }
}
