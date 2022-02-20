package com.xjs._36wallpaper.task;

import com.xjs._36wallpaper.service._36wallpaperService;
import com.xjs._36wallpaper.webmagic._36wallpaperProcessor;
import com.xjs.annotation.ReptileLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ReptileConst._36_WALLPAPER_URL;

/**
 * 36壁纸网爬虫任务
 * @author xiejs
 * @since 2022-02-20
 */
@Component
@Log4j2
public class _36wallpaperTask {

    @Autowired
    private _36wallpaperProcessor wallpaperProcessor;

    @Autowired
    private _36wallpaperService wallpaperService;


    /**
     * 提供定时任务调取
     * @return 循环次数
     */
    @ReptileLog(name = "36壁纸网", url = _36_WALLPAPER_URL)
    public Long reptileWallpaper() {
        Long run = wallpaperProcessor.run();
        //删除重复数据
        int count = wallpaperService.deleteRepeatData();
        log.info("36壁纸删除重复数据数：" + count);

        return run;
    }

}
