package com.xjs.job.task.webmagic;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicWeiXinSouGouFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 爬虫 微信搜狗 定时任务
 * @author xiejs
 * @since 2022-02-22
 */
@Component("WeiXinSouGouTask")
@Log4j2
public class WeiXinSouGouTask {

    @Resource
    private RemoteWebmagicWeiXinSouGouFeign remoteWebmagicWeiXinSouGouFeign;

    @TaskLog(name = "微信搜狗爬虫任务")
    public void weiXinSouGou() {
        log.info("---------------爬虫-微信搜狗定时任务Start-------------------");

        R r = remoteWebmagicWeiXinSouGouFeign.WeiXinSouGouTaskForPRC();

        log.info("爬虫-微信搜狗定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-微信搜狗定时任务end---------------------");
    }

}
