package com.xjs.job.task.webmagic;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicSinaFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 爬虫 新浪新闻 定时任务
 * @author xiejs
 * @since 2022-02-15
 */
@Component("SinaTask")
@Log4j2
public class SinaTask {
    @Resource
    private RemoteWebmagicSinaFeign remoteWebmagicSinaFeign;

    /**
     * 爬虫 新浪新闻 定时任务执行
     */
    @TaskLog(name = "新浪新闻爬虫任务")
    public void sinaNews() {
        log.info("---------------爬虫-新浪新闻定时任务Start-------------------");

        R r = remoteWebmagicSinaFeign.sinaTaskForPRC();

        log.info("爬虫-新浪新闻定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-新浪新闻定时任务end---------------------");
    }
}
