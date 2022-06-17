package com.xjs.y2048community.webmagic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.consts.ReptileConst;
import com.xjs.y2048community.consts.InitConst;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.REPTILE_2048_COMMUNITY_COUNT;
import static com.xjs.consts.ReptileConst.Y_2048_COMMUNITY_URL;

/**
 * 社区 爬虫处理
 *
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

        //  开关获取所有页面

        //  根据每天日期获取最新

        // todo 网络情况好的时候爬取所有页面


        try {
            Integer count = redisService.getCacheObject(REPTILE_2048_COMMUNITY_COUNT);
            if (count == null) {
                count = 0;
            }

            List<Selectable> trs = page.getHtml().css("#content .tr3").nodes();

            if (CollUtil.isNotEmpty(trs)) {
                for (Selectable tr : trs) {
                    List<Selectable> selectables = tr.css("th > span > a").nodes();

                    for (Selectable selectable : selectables) {
                        if (selectable.css("a", "text").get().equals("COSPLAY") ||
                                selectable.css("a", "text").get().equals("高跟絲襪")
                        ) {

                            //String href = selectable.css("a", "href").get();

                            String href = selectable.links().get();

                            //获取需要爬取的路径
                            page.addTargetRequest(href);
                        }
                    }


                    count += selectables.size();

                }

            }

            this.handlerListPage(page, count);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            redisService.expire(REPTILE_2048_COMMUNITY_COUNT, 3, TimeUnit.HOURS);
        }
    }

    /**
     * 处理列表页面
     *
     * @param page  页面
     * @param count 总数
     */
    private void handlerListPage(Page page, Integer count) {
        try {
            String div = page.getHtml().css("#ajaxtable").get();

            String bread = page.getHtml().css("#breadCrumb").get();

            //获取所有分页页面
            if (InitConst.CONTROL) {
                if (StringUtils.isNotEmpty(bread)) {
                    if ((bread.contains("COSPLAY") || bread.contains("高跟絲襪")) && !page.getUrl().get().contains("-page-")) {
                        String pages = page.getHtml().css(".pagesone > span", "text").get();
                        if (StringUtils.isNotEmpty(pages)) {
                            if (pages.contains("/")) {
                                String[] split = pages.split("/");
                                String num = split[split.length - 1];

                                int numInt = Integer.parseInt(num);
                                for (int i = 2; i <= numInt; i++) {
                                    String url = page.getUrl().get();
                                    String[] splitUrl = url.split(".html");
                                    //String newUrl = splitUrl[0] + "-page-" + i + ".html";

                                    StringBuffer sb = new StringBuffer();
                                    sb.append(splitUrl[0]).append("-page-").append(i).append(".html");

                                    page.addTargetRequest(sb.toString());
                                }

                            }
                        }
                    }
                }
            } else if (StringUtils.isNotEmpty(bread)) {
                if ((bread.contains("COSPLAY") || bread.contains("高跟絲襪")) && !page.getUrl().get().contains("-page-")) {
                    String pages = page.getHtml().css(".pagesone > span", "text").get();
                    if (StringUtils.isNotEmpty(pages)) {
                        String url = page.getUrl().get();
                        String[] splitUrl = url.split(".html");
                        List<String> asList = Arrays.asList(splitUrl[0] + "-page-2.html", splitUrl[0] + "-page-3.html");
                        page.addTargetRequests(asList);
                    }
                }
            }


            if (StringUtils.isNotEmpty(div)) {

                /*List<Selectable> subjects = page.getHtml().css("#ajaxtable .tr3 .tal").nodes();

                for (int i = 0; i < subjects.size(); i++) {
                    if (i <= 18) {
                        continue;
                    }

                    List<String> hrefs = subjects.get(i).css(".subject", "href").all();

                    //subjects.get(i).css()

                    List<String> collect = hrefs.stream().map(href -> Y_2048_COMMUNITY_URL + href).collect(Collectors.toList());

                    count += collect.size();

                    page.addTargetRequests(collect);
                }*/

                List<Selectable> trs = page.getHtml().css("#ajaxtable .tr3 ").nodes();

                for (Selectable tr : trs) {
                    String date = tr.css("td:nth-child(3) div", "text").get();

                    //不是当天的数据页面跳出
                    if (StringUtils.isNotEmpty(date) && !InitConst.CONTROL) {
                        if (!DateUtil.today().equals(date)) {
                            continue;
                        }
                    }

                    String href = tr.css("td .subject", "href").get();
                    if (StringUtils.isNotEmpty(href)) {
                        String url = Y_2048_COMMUNITY_URL + href;
                        page.addTargetRequest(url);
                    }

                }

            }


            //this.handlerDetailPage(page, count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理详情页面
     *
     * @param page  页面
     * @param count 总数
     */
    private void handlerDetailPage(Page page, Integer count) {
        try {
            String div = page.getHtml().css("#read_tpc").get();

            if (StringUtils.isNotEmpty(div)) {
                //获取图片链接
                List<String> srcs = page.getHtml().css("#read_tpc > .att_img > img", "src").all();
                page.putField("srcs", srcs);

                //获取标题
                String title = page.getHtml().css("#subject_tpc", "text").get();
                page.putField("title", title);

                //获取分类
                String type = Optional.ofNullable(page.getHtml().css("#breadCrumb > a:nth-child(3)", "text").get()).orElse("未知");
                page.putField("type", type);
            }

        } finally {
            redisService.setCacheObject(REPTILE_2048_COMMUNITY_COUNT, count);
        }
    }


    @Override
    public Site getSite() {
        return Site.me()
                .addHeader(ReptileConst.headerKey, ReptileConst.headerValue)
                .addHeader("Connection", "close")
                .setCharset("utf8")//设置字符编码
                .setTimeOut(10000)//设置超时时间
                .setRetrySleepTime(100)//设置重试间隔时间
                .setCycleRetryTimes(2)//设置重试次数
                .setSleepTime(10)//设置两个页面之间的间隔时间
                ;
    }
}
