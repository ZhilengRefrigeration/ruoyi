package com.xjs.job.task.webmagic;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicSinaFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
     * 任务执行
     */
    public void sinaNews() {
        log.info("---------------爬虫-新浪新闻定时任务Start-------------------");
        LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();

        R r = remoteWebmagicSinaFeign.sinaTaskForPRC();

        log.info("爬虫-新浪新闻定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
        long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
        log.info("爬虫-新浪新闻定时任务Job耗费时间:{}ms", between);
        log.info("---------------爬虫-新浪新闻定时任务end---------------------");
    }
}
