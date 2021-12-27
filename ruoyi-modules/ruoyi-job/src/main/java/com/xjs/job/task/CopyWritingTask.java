package com.xjs.job.task;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.english.api.RemoteCopyWritingFeign;
import com.xjs.business.english.api.domain.CopyWriting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc  调用文案定时任务
 * @create 2021-12-27
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
        log.info("文案定时任务Start");
        R<CopyWriting> r = remoteCopyWritingFeign.copyWriting();
        log.info("文案定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
    }
}
