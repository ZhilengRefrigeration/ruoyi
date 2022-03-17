package com.xjs.weixin.task;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.annotation.ReptileLog;
import com.xjs.weixin.webmagic.WeiXinLinkPipeline;
import com.xjs.weixin.webmagic.WeiXinLinkProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_LINK_COUNT;

/**
 * 微信链接爬虫任务
 * @author xiejs
 * @since 2022-03-17
 */
@Component
@Log4j2
public class WeiXinLinkTask {

    @Autowired
    private WeiXinLinkProcessor weiXinLinkProcessor;
    @Autowired
    private WeiXinLinkPipeline weiXinLinkPipeline;
    @Autowired
    private RedisService redisService;

    @ReptileLog(name = "微信链接", url = "###")
    public Long reptileWeiXinLink(String link) {
        //执行爬虫
        Spider.create(weiXinLinkProcessor)
                .addUrl(link)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))//设置url去重过滤器
                .addPipeline(weiXinLinkPipeline)//设置爬取之后的数据操作
                //.setDownloader(downloader)//设置下载器
                .run();//执行

        Integer cache = redisService.getCacheObject(REPTILE_WEIXIN_LINK_COUNT);
        redisService.deleteObject(REPTILE_WEIXIN_LINK_COUNT);
        if (cache != null) {
            return Long.valueOf(cache);
        }
        return 0L;
    }

}
