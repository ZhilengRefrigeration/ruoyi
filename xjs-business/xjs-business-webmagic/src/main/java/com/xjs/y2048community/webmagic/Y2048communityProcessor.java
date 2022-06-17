package com.xjs.y2048community.webmagic;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.consts.ReptileConst;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.REPTILE_2048_COMMUNITY_COUNT;
import static com.xjs.consts.ReptileConst.Y_2048_COMMUNITY_URL;

/**
 * 社区 爬虫处理
 * @author xiejs
 * @since 2022-06-17
 */
@Component
@Log4j2
public class Y2048communityProcessor implements PageProcessor {

    @Autowired
    private RedisService redisService;

    @Override
    public void process(Page page) {

        try {
            Integer count = redisService.getCacheObject(REPTILE_2048_COMMUNITY_COUNT);
            if (count == null) {
                count = 0;
            }

            List<Selectable> trs = page.getHtml().css("#content .tr3").nodes();
            for (Selectable tr : trs) {
                List<String> allText = tr.css("th > span > a", "text").all();

                for (String text : allText) {
                    if ("COSPLAY".equalsIgnoreCase(text)) {
                        String href = tr.css("th > span > a", "href").get();

                        //获取需要爬取的路径
                        page.addTargetRequest(Y_2048_COMMUNITY_URL+href);
                    }
                    if ("高跟絲襪".equalsIgnoreCase(text)) {
                        String href = tr.css("th > span > a", "href").get();

                        //获取需要爬取的路径
                        page.addTargetRequest(Y_2048_COMMUNITY_URL+href);
                    }
                }

                count += allText.size();

            }

            this.handlerListPage(page, count);




            redisService.setCacheObject(REPTILE_2048_COMMUNITY_COUNT, count);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            redisService.expire(REPTILE_2048_COMMUNITY_COUNT, 3, TimeUnit.HOURS);
        }
    }

    /**
     *  处理列表页面
     * @param page 页面
     * @param count 总数
     */
    private void handlerListPage(Page page, Integer count) {
        page.getHtml().css("#ajaxtable > .tr3 > .tal >");
    }



    @Override
    public Site getSite() {
        return Site.me()
                .addHeader(ReptileConst.headerKey, ReptileConst.headerValue)
                .setCharset("utf8")//设置字符编码
                .setTimeOut(5000)//设置超时时间
                .setRetrySleepTime(500)//设置重试间隔时间
                .setCycleRetryTimes(5)//设置重试次数
                .setSleepTime(10)//设置两个页面之间的间隔时间
                ;
    }
}
