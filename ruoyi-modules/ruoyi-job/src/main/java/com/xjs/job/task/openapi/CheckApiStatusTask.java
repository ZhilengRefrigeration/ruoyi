package com.xjs.job.task.openapi;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteCommonFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public void checkApiStatus() {
        log.info("---------------检查api状态定时任务Start-------------------");
        LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();

        R r = remoteCommonFeign.CheckApiStatusForRPC();

        log.info("检查api状态定时任务结果:code={},msg={}",r.getCode(),r.getMsg());

        LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
        long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
        log.info("检查api状态定时任务Job耗费时间:{}ms", between);
        log.info("---------------检查api状态定时任务end---------------------");


    }
}
