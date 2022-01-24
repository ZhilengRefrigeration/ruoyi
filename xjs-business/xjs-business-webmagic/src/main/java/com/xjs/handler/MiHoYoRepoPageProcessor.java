package com.xjs.handler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 米哈游社区爬取资源
 * @author xiejs
 * @since 2022-01-24
 */
public class MiHoYoRepoPageProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }



}
