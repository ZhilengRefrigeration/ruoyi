package com.xjs.common.task;

import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.topsearch.service.ApiTopsearchAllnetworkService;
import com.xjs.topsearch.service.ApiTopsearchWechatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private ApiTopsearchAllnetworkService apiTopsearchAllnetworkService;
    @Autowired
    private ApiTopsearchWechatService apiTopsearchWechatService;


    /**
     * 2022-01-07 07:00:00
     * 2022-01-07 08:00:00
     * 2022-01-07 09:00:00
     * 2022-01-07 10:00:00
     */
    @Scheduled(cron = "0 0 7-23 * * ? ")
    public void execute() {
        int copyWritingCount = copyWritingService.deleteRepeatData();
        log.info("thread id:{},定时清除文案重复数据，重复数：{}", Thread.currentThread().getId(),copyWritingCount);
        Integer allNetworkCount = apiTopsearchAllnetworkService.deleteRepeatData();
        log.info("thread id:{},定时清除全网热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),allNetworkCount);
        Integer wechatCount = apiTopsearchWechatService.deleteRepeatData();
        log.info("thread id:{},定时清除微信热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),wechatCount);
    }
}
