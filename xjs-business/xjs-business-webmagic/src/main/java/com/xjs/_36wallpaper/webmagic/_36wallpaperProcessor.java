package com.xjs._36wallpaper.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 36壁纸网站 爬虫处理
 *
 * @author xiejs
 * @since 2022-02-19
 */
public class _36wallpaperProcessor implements PageProcessor {

    private static final String URL = "https://www.3gbizhi.com/";


    private Site site = Site.me()
            .setCharset("uft8")//设置字符编码
            .setTimeOut(10000)//设置超时时间
            .setRetrySleepTime(2000)//设置重试间隔时间
            .setCycleRetryTimes(3)//设置重试次数
            .setSleepTime(10)//设置两个页面之间的间隔时间
        ;

    public void setSite(Site site) {
        this.site = site;
    }

    //解析页面
    @Override
    public void process(Page page) {
        //解析返回的数据page,并且把解析的结果放到ResultItems中

        /*//第一种写法:css选择器
        page.putField("url_css", page.getHtml().css(".tmenu li a").all());


        //第二种写法:xpath
        page.putField("url_xpath",page.getHtml().xpath("//ul[@class=tmenu]/li/a").all());


        //第三种写法:正则表达式
        page.putField("url_regex", page.getHtml().css(".tmenu li a").regex(".*图片*.*").all());*/

        // todo 爬取36壁纸图片


        //获取链接
        page.addTargetRequests(page.getHtml().css(".tmenu li").links().all());
        page.putField("url", page.getHtml().css(".imgw").links().all());

    }


    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 执行爬虫
     */
    public void run() {
        Spider.create(new _36wallpaperProcessor()).addUrl(URL).thread(5).runAsync();
    }


    public static void main(String[] args) {
        Spider.create(new _36wallpaperProcessor()).addUrl("https://www.3gbizhi.com/").thread(5).run();

    }
}
