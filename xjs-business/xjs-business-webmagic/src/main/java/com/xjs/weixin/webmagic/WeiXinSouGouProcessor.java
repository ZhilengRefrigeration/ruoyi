package com.xjs.weixin.webmagic;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.weixin.pojo.WeiXinSouGou;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_SOUGOU_COUNT;
import static com.xjs.consts.ReptileConst.WEIXIN_SOUGOU_URL;

/**
 * 微信搜狗网 爬虫处理
 *
 * @author xiejs
 * @since 2022-02-21
 */
@Log4j2
@Component
public class WeiXinSouGouProcessor implements PageProcessor {

    @Autowired
    private WeiXinSouGouPipeline weiXinSouGouPipeline;

    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        WeiXinSouGouProcessor.redisService = redisService;
    }


    @Override
    public void process(Page page) {
        try {
            //复杂度计算
            //循环次数存入redis中
            Integer count = redisService.getCacheObject(REPTILE_WEIXIN_SOUGOU_COUNT);
            if (count == null) {
                count = 0;
            }

            List<Selectable> nodes = page.getHtml().css("#type_tab > .fieed-box a").nodes();

            //1、获取需要爬取的路径
            Set<String> set = new HashSet<>();
            for (Selectable node : nodes) {
                count++;

                String s = node.get();

                Document parse = Jsoup.parse(s);

                String id = parse.select("a").attr("id");

                set.add(id);
            }
            set.removeIf(s -> !s.contains("pc_"));
            List<String> list = new ArrayList<>();
            for (String s : set) {
                count++;

                s = WEIXIN_SOUGOU_URL + "/pcindex/pc/" + s + "/" + s + ".html";
                list.add(s);
            }

            //2、把所有连接加入到队列
            page.addTargetRequests(list);

            //3、获取需要的参数
            List<Selectable> newsNodes = page.getHtml().css(".news-list > li").nodes();
            List<WeiXinSouGou> weiXinSouGouList = new ArrayList<>();
            for (Selectable newsNode : newsNodes) {
                count++;

                String s = newsNode.get();

                //文章具体路径
                String link = newsNode.css(".img-box > a", "href").get();

                //图片路径
                String imgSrc = newsNode.css(".img-box > a > img", "src").get();

                //标题
                String title = newsNode.css(".txt-box > h3 > a", "text").get();

                //省略的内容
                String content = newsNode.css(".txt-box > .txt-info", "text").get();

                //来源
                String source = newsNode.css(".s-p > a", "text").get();

                WeiXinSouGou weiXinSouGou = new WeiXinSouGou()
                        .setUrl(link)
                        .setImgUrl(imgSrc)
                        .setTitle(title)
                        .setContent(content)
                        .setSource(source);
                weiXinSouGouList.add(weiXinSouGou);
            }

            page.putField("weiXinSouGouList",weiXinSouGouList);

            redisService.setCacheObject(REPTILE_WEIXIN_SOUGOU_COUNT, count + 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisService.expire(REPTILE_WEIXIN_SOUGOU_COUNT, 3, TimeUnit.HOURS);
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

    /**
     * 执行爬虫
     *
     * @return 返回循环次数
     */
    public Long run() {
        //执行爬虫
        Spider.create(new WeiXinSouGouProcessor())
                .addUrl(WEIXIN_SOUGOU_URL)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))//设置url去重过滤器
                .addPipeline(weiXinSouGouPipeline)//设置爬取之后的数据操作
                //.setDownloader(downloader)//设置下载器
                .run();//执行

        //从redis中获取循环次数
        Integer cache = redisService.getCacheObject(REPTILE_WEIXIN_SOUGOU_COUNT);
        redisService.deleteObject(REPTILE_WEIXIN_SOUGOU_COUNT);

        if (cache != null) {
            return Long.valueOf(cache);
        }

        return 0L;
    }


}
