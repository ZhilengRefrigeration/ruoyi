package com.xjs.job.task.openapi;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteTopSearchFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 热搜榜定时任务
 * @author xiejs
 * @since 2022-01-26
 */
@Component("TopSearchTask")
@Log4j2
public class TopSearchTask {

    @Resource
    private RemoteTopSearchFeign remoteTopSearchFeign;

    /**
     * 定时获取热搜榜
     */
    @TaskLog(name = "热搜榜任务")
    public void getTopSearch() {
        log.info("---------------热搜榜定时任务Start-------------------");

        R<Map<String, List>> mapR = remoteTopSearchFeign.topSearchForRPC();
        log.info("热搜榜定时任务结果:code={},msg={}",mapR.getCode(),mapR.getMsg());

        log.info("---------------热搜榜定时任务end---------------------");

    }

}
