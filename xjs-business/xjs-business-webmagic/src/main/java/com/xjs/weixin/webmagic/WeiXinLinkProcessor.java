package com.xjs.weixin.webmagic;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_LINK_COUNT;

/**
 * 微信链接爬虫
 *
 * @author xiejs
 * @since 2022-03-17
 */
@Component
@Log4j2
public class WeiXinLinkProcessor implements PageProcessor {

    @Autowired
    private RedisService redisService;

    @Override
    public void process(Page page) {
        try {
            Integer count = redisService.getCacheObject(REPTILE_WEIXIN_LINK_COUNT);
            if (count == null) {
                count = 0;
            }

            //获取图片url
            List<String> linkList = page.getHtml().css("img", "data-src").all();
            //去空
            linkList.removeIf(StringUtils::isBlank);

            //获取标题
            String title = page.getHtml().css("#activity-name","text").get();

            page.putField("title",title);
            page.putField("linkList",linkList);

            log.info("linkList----{}",linkList);

            count= linkList.size();

            redisService.setCacheObject(REPTILE_WEIXIN_LINK_COUNT, count );
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            redisService.expire(REPTILE_WEIXIN_LINK_COUNT, 3, TimeUnit.HOURS);
        }
    }

    @Override
    public Site getSite() {
        return Site.me()
                //.addHeader(headerKey, headerValue)
                .setCharset("utf8")//设置字符编码
                .setTimeOut(2000)//设置超时时间
                .setRetrySleepTime(100)//设置重试间隔时间
                .setCycleRetryTimes(10)//设置重试次数
                .setSleepTime(1)//设置两个页面之间的间隔时间
                ;
    }
}
