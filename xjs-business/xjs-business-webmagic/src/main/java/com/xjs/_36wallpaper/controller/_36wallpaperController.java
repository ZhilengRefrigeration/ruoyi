package com.xjs._36wallpaper.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs._36wallpaper.task._36wallpaperTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 36壁纸网爬虫controller
 * @author xiejs
 * @since 2022-02-20
 */
@RestController
@RequestMapping("_36wallpaper")
@Api(tags = "爬虫模块-36壁纸网")
public class _36wallpaperController {

    @Autowired
    private _36wallpaperTask wallpaperTask;


    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R _36wallpaperControllerTaskForPRC() {
        Long count = wallpaperTask.reptileWallpaper();
        return R.ok(count);
    }
}
