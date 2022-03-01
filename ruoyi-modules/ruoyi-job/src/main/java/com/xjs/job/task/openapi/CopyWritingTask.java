package com.xjs.job.task.openapi;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteCopyWritingFeign;
import com.xjs.business.api.domain.CopyWriting;
import com.xjs.job.aop.TaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
     *
     * 任务执行
     */
    @TaskLog(name = "文案任务")
    public void execute() {
        log.info("---------------文案定时任务Start-------------------");

        //该循环会导致执行i次重复删除sql
        for (int i = 0; i < 8; i++) {
            R<CopyWriting> r = remoteCopyWritingFeign.copyWriting();
            log.info("文案定时任务[{}]结果:code={},msg={},data={}",i,r.getCode(),r.getMsg(),r.getData());
        }

        log.info("---------------文案定时任务end---------------------");
    }
}
