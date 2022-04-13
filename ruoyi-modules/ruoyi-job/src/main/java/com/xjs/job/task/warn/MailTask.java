package com.xjs.job.task.warn;

import com.xjs.business.warning.RemoteMailFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时发送邮件任务
 * @author xiejs
 * @since 2022-04-13
 */
@Component("MailTask")
@Log4j2
public class MailTask {

    @Resource
    private RemoteMailFeign remoteMailFeign;

    @TaskLog(name = "邮件天气播报任务")
    public void execution() {
        log.info("---------------邮件天气任务定时任务Start-------------------");
        remoteMailFeign.sendWeatherMailForRPC();
        log.info("---------------邮件天气任务定时任务End-------------------");
    }

}
