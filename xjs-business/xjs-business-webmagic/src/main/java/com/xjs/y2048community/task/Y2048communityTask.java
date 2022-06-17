package com.xjs.y2048community.task;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.annotation.ReptileLog;
import com.xjs.y2048community.webmagic.Y2048communityPipeline;
import com.xjs.y2048community.webmagic.Y2048communityProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import static com.xjs.consts.RedisConst.REPTILE_2048_COMMUNITY_COUNT;
import static com.xjs.consts.ReptileConst.Y_2048_COMMUNITY_URL;

/**
 * 2048社区任务
 * @author xiejs
 * @since 2022-06-17
 */
@Component
public class Y2048communityTask {
    @Autowired
    private Y2048communityProcessor y2048communityProcessor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private Y2048communityPipeline y2048communityPipeline;


    @ReptileLog(name = "2048社区",url = Y_2048_COMMUNITY_URL)
    public Long reptile() {
        //执行爬虫
        Spider.create(y2048communityProcessor)
                .addUrl(Y_2048_COMMUNITY_URL)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))
                .addPipeline(y2048communityPipeline)//设置爬取之后的数据操作
                .run();//同步执行

        Integer cache = redisService.getCacheObject(REPTILE_2048_COMMUNITY_COUNT);
        redisService.deleteObject(REPTILE_2048_COMMUNITY_COUNT);
        if (cache != null) {
            return Long.valueOf(cache);
        }
        return 0L;
    }
}
