package com.xjs.job.task.openapi;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteCommonFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 检查api状态任务定时任务
 * @author xiejs
 * @since 2022-02-21
 */
@Component("CheckApiStatusTask")
@Log4j2
public class CheckApiStatusTask {

    @Resource
    private RemoteCommonFeign remoteCommonFeign;

    @TaskLog(name = "检查API状态任务")
    public void checkApiStatus() {
        log.info("---------------检查api状态定时任务Start-------------------");

        R r = remoteCommonFeign.CheckApiStatusForRPC();

        log.info("检查api状态定时任务结果:code={},msg={}",r.getCode(),r.getMsg());

        log.info("---------------检查api状态定时任务end---------------------");


    }
}
