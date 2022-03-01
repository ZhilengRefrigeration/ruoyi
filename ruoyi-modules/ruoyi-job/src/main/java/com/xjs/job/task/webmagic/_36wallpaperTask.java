package com.xjs.job.task.webmagic;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagic36wallpaperFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 爬虫 36壁纸网 定时任务
 * @author xiejs
 * @since 2022-02-20
 */
@Component("Wallpaper_36Task")
@Log4j2
public class _36wallpaperTask {

    @Resource
    private RemoteWebmagic36wallpaperFeign remoteWebmagic36wallpaperFeign;

    /**
     * 爬虫 36壁纸网 定时任务执行
     */
    @TaskLog(name = "36壁纸爬虫任务")
    public void _36wallpaper() {
        log.info("---------------爬虫-36壁纸网定时任务Start-------------------");

        R r = remoteWebmagic36wallpaperFeign._36wallpaperTaskForPRC();

        log.info("爬虫-36壁纸网定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-36壁纸网定时任务end---------------------");

    }

}
