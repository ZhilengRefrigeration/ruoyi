package com.xjs.job.task;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteTopSearchFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public void getTopSearch() {
        log.info("---------------热搜榜定时任务Start-------------------");
        LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();

        R<Map<String, List>> mapR = remoteTopSearchFeign.topSearchForRPC();
        log.info("热搜榜定时任务结果:code={},msg={}",mapR.getCode(),mapR.getMsg());

        LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
        long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
        log.info("热搜榜定时任务Job耗费时间:{}ms", between);
        log.info("---------------热搜榜定时任务end---------------------");

    }

}
