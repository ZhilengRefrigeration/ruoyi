package com.xjs.common.task;

import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.topsearch.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * openapi模块内置定时任务
 * @author xiejs
 * @since  2022-01-06
 */
@Component
@Log4j2
public class OpenApiTask {
    @Autowired
    private CopyWritingService copyWritingService;

    @Autowired
    private TopSearchService topSearchService;


    /**
     * 删除重复文案数据<br>
     * 2022-01-07 07:00:00<br>
     * 2022-01-07 08:00:00<br>
     * 2022-01-07 09:00:00<br>
     * 2022-01-07 10:00:00<br>
     */
    @Scheduled(cron = "0 0 10,14,20 * * ? ")
    public void deleteRepeat() {
        int copyWritingCount = copyWritingService.deleteRepeatData();
        log.info("thread id:{},定时清除文案重复数据，重复数：{}", Thread.currentThread().getId(),copyWritingCount);
        Integer integer = topSearchService.deleteRepeat();
        log.info("thread id:{},定时清除重复数据总数，重复数：{}", Thread.currentThread().getId(),integer);

    }

    /**
     * 定时获取热搜榜<br>
     * 2022-01-22 11:11:00<br>
     * 2022-01-22 11:22:00<br>
     * 2022-01-22 11:33:00<br>
     * 2022-01-22 11:44:00<br>
     * 2022-01-22 11:55:00<br>
     */
    @Scheduled(cron = "0 0/11 * * * ? ")
    public void getTopSearch() {
        log.info("thread id:{},定时获取热搜榜数据", Thread.currentThread().getId());
        topSearchService.getAllTopSearch();
    }
}
