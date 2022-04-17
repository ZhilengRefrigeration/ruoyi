package com.xjs.zol.task;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.annotation.ReptileLog;
import com.xjs.zol.webmagic.ZolPhonePipeline;
import com.xjs.zol.webmagic.ZolPhoneProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import static com.xjs.consts.RedisConst.REPTILE_ZOL_PHONE_COUNT;
import static com.xjs.consts.ReptileConst.ZOL_PHONE_URL;

/**
 * 中关村产品报价爬取手机任务
 *
 * @author xiejs
 * @since 2022-04-17
 */
@Component
@Log4j2
public class ZolPhoneTask {

    @Autowired
    private ZolPhoneProcessor zolPhoneProcessor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ZolPhonePipeline zolPhonePipeline;


    @ReptileLog(name = "中关村手机", url = ZOL_PHONE_URL)
    public Long reptileZolPhone() {
        //执行爬虫
        Spider.create(zolPhoneProcessor)
                .addUrl(ZOL_PHONE_URL)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))//设置url去重过滤器
                .addPipeline(zolPhonePipeline)//设置爬取之后的数据操作
                //.setDownloader(downloader)//设置下载器
                .run();//同步执行

        Integer cache = redisService.getCacheObject(REPTILE_ZOL_PHONE_COUNT);
        redisService.deleteObject(REPTILE_ZOL_PHONE_COUNT);
        if (cache != null) {
            return Long.valueOf(cache);
        }
        return 0L;
    }
}
