package com.xjs.job.task.webmagic;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicY2048communityFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 2048定时任务
 * @author xiejs
 * @since 2022-06-20
 */
@Component("Y2048communityTask")
@Log4j2
public class Y2048communityTask {
    @Resource
    private RemoteWebmagicY2048communityFeign remoteWebmagicY2048communityFeign;

    @TaskLog(name = "2048社区爬虫任务")
    public void execute() {
        log.info("---------------爬虫-2048定时任务Start-------------------");

        R r = remoteWebmagicY2048communityFeign.y2048communityTaskForPRC();

        log.info("爬虫-2048定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-2048定时任务end---------------------");
    }

}
