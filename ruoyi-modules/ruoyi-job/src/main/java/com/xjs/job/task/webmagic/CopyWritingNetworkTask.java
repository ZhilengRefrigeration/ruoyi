package com.xjs.job.task.webmagic;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicCopyWritingNetworkFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 爬虫 文案网 定时任务
 * @author xiejs
 * @since 2022-02-17
 */
@Component("CopyWritingNetworkTask")
@Log4j2
public class CopyWritingNetworkTask {

    @Resource
    private RemoteWebmagicCopyWritingNetworkFeign remoteWebmagicCopyWritingNetworkFeign;

    /**
     * 爬虫 文案网 定时任务执行
     */
    @TaskLog(name = "文案网爬虫任务")
    public void copyWritingNetwork() {
        log.info("---------------爬虫-文案网定时任务Start-------------------");

        R r = remoteWebmagicCopyWritingNetworkFeign.copyWritingNetworkTaskForPRC();

        log.info("爬虫-文案网定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-文案网定时任务end---------------------");
    }
}
