package com.xjs.job.task.openapi;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteCopyWritingFeign;
import com.xjs.business.api.domain.CopyWriting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 调用文案定时任务
 * @author xiejs
 * @since  2021-12-27
 */
@Component("CopyWritingTask")
public class CopyWritingTask {

    @Resource
    private RemoteCopyWritingFeign remoteCopyWritingFeign;

    private static final Logger log = LoggerFactory.getLogger(CopyWritingTask.class);

    /**
     * 任务执行
     */
    public void execute() {
        log.info("---------------文案定时任务Start-------------------");
        for (int i = 0; i < 6; i++) {
            LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();
            R<CopyWriting> r = remoteCopyWritingFeign.copyWriting();
            log.info("文案定时任务[{}]结果:code={},msg={},data={}",i,r.getCode(),r.getMsg(),r.getData());
            LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
            long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
            log.info("文案[{}]定时任务Job耗费时间:{}ms", i,between);
        }
        log.info("---------------文案定时任务end---------------------");
    }
}
