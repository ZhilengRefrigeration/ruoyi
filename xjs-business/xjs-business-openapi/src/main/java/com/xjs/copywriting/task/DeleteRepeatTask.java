package com.xjs.copywriting.task;

import com.xjs.copywriting.service.CopyWritingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc 删除重复文案数据
 * @create 2022-01-06
 */
@Component
@Log4j2
public class DeleteRepeatTask {
    @Resource
    private CopyWritingService copyWritingService;

    /**
     * 2022-01-07 07:00:00
     * 2022-01-07 08:00:00
     * 2022-01-07 09:00:00
     * 2022-01-07 10:00:00
     */
    @Scheduled(cron = "0 0 7-23 * * ? ")
    public void execute() {
        int count = copyWritingService.deleteRepeatData();
        log.info("thread id:{},定时清除文案重复数据，重复数：{}", Thread.currentThread().getId(),count);
    }
}
