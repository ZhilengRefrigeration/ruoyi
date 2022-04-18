package com.xjs.job.task.webmagic;

import com.xjs.business.webmagic.RemoteWebmagicZolFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 爬虫 中关村数据 定时任务
 * @author xiejs
 * @since 2022-04-18
 */
@Component("ZolTask")
@Log4j2
public class ZolTask {
    @Resource
    private RemoteWebmagicZolFeign remoteWebmagicZolFeign;


    @TaskLog(name = "微信搜狗爬虫任务")
    public void zol(){
        log.info("---------------爬虫-中关村数据定时任务Start-------------------");

        remoteWebmagicZolFeign.ZolPhoneTaskForRPC();

        log.info("---------------爬虫-中关村数据定时任务end---------------------");
    }

}
