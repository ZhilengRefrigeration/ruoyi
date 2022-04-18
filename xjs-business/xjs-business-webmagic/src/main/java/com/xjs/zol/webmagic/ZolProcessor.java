package com.xjs.zol.webmagic;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.zol.pojo.ZolNotebook;
import com.xjs.zol.pojo.ZolPhone;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.REPTILE_ZOL_PHONE_COUNT;

/**
 * 中关村产品报价爬取处理
 *
 * @author xiejs
 * @since 2022-04-17
 */
@Log4j2
@Component
@SuppressWarnings("all")
public class ZolProcessor implements PageProcessor {

    public static final String URL = "https://detail.zol.com.cn/";

    @Autowired
    private RedisService redisService;

    @Override
    public void process(Page page) {

        try {
            Integer count = redisService.getCacheObject(REPTILE_ZOL_PHONE_COUNT);
            if (count == null) {
                count = 0;
            }

            List<Selectable> nodes = page.getHtml().css("#J_CategoryItems > .item > h3 > a").nodes();
            for (Selectable node : nodes) {

                if ("手机".equals(node.css("a", "text").get())) {
                    String href = node.css("a", "href").get();
                    page.addTargetRequests(Collections.singletonList(href));
                }
                if ("笔记本".equals(node.css("a", "text").get())) {
                    String href = node.css("a", "href").get();
                    page.addTargetRequests(Collections.singletonList(href));
                }

            }

            //获取其他页面放入队列中
            //等待爬虫的页面后缀
            String html_href = page.getHtml().css(".page-box > .pagebar > .next", "href").get();

            Thread.sleep(1);

            page.addTargetRequests(Collections.singletonList(html_href));

            //获取当前页面是手机还是电脑
            String title = page.getHtml().css(".wrapper > .breadcrumb-filter-selected > .breadcrumb > span", "text").get();

            //拿到每个li 标签
            List<Selectable> lis = page.getHtml().css("#J_PicMode > li").nodes();
            if (StringUtils.isNotEmpty(title) && title.contains("手机")) {

                List<ZolPhone> zolPhoneList = new ArrayList<>();

                for (Selectable li : lis) {
                    ZolPhone zolPhone = new ZolPhone();

                    //排除无用数据
                    if ("display:none;".equals(li.css("li", "style").get())) {
                        continue;
                    }

                    //获取手机的详情页面url
                    String href = li.css("li > .pic", "href").get();

                    zolPhone.setDetailPage(URL + href);

                    //获取手机的名称
                    String phoneName = li.css("li > h3 > a", "text").get();
                    zolPhone.setPhoneName(phoneName);

                    //获取手机的描述
                    String desc = li.css("li > h3 > a > span", "text").get();
                    zolPhone.setDescription(desc);

                    //获取手机的参考价
                    String price = li.css("li > .price-row .price-type", "text").get();
                    //排除无用数据
                    if (StringUtils.isNotBlank(price)) {
                        //检查是否是数字
                        boolean creatable = NumberUtils.isCreatable(price);
                        if (creatable) {
                            zolPhone.setPrice(new BigDecimal(price));
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }

                    //获取手机的评分
                    String heat = li.css("li > .comment-row > .score", "text").get();
                    if (StringUtils.isNotBlank(heat)) {
                        boolean creatable = NumberUtils.isCreatable(heat);
                        if (creatable) {
                            zolPhone.setHeat(new BigDecimal(heat));
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }

                    //获取手机图片的地址
                    String picture = li.css("li > .pic > img", ".src").get();
                    zolPhone.setPictureUrl(picture);

                    zolPhoneList.add(zolPhone);

                    //计数
                    count++;
                }

                page.putField("zolPhoneList", zolPhoneList);

            }

            if (StringUtils.isNotEmpty(title) && title.contains("笔记本")) {
                ArrayList<ZolNotebook> zolNotebooks = new ArrayList<>();

                for (Selectable li : lis) {
                    ZolNotebook zolNotebook = new ZolNotebook();

                    //排除无用数据
                    if ("display:none;".equals(li.css("li", "style").get())) {
                        continue;
                    }

                    //获取笔记本的详情页面url
                    String href = li.css("li > .pic", "href").get();
                    zolNotebook.setDetailPage(URL + href);

                    //获取笔记本的名称
                    String notebookName = li.css("li > h3 > .title-black > a", "text").get();
                    zolNotebook.setNotebookName(notebookName);

                    //获取笔记本的描述
                    String desc = li.css("li > h3 > a", "text").get();
                    zolNotebook.setDescription(desc);

                    //获取笔记本的参考价
                    String price = li.css("li > .price-row .price-type", "text").get();
                    //排除无用数据
                    if (StringUtils.isNotBlank(price)) {
                        if ("停产".equals(price)
                                || "概念产品".equals(price)
                                || "即将上市".equals(price)
                                || "暂无报价".equals(price)) {
                            continue;
                        }
                        zolNotebook.setPrice(price);
                    } else {
                        continue;
                    }

                    //获取笔记本的评分
                    String heat = li.css("li > .comment-row > .score", "text").get();
                    if (StringUtils.isNotBlank(heat)) {
                        boolean creatable = NumberUtils.isCreatable(heat);
                        if (creatable) {
                            zolNotebook.setHeat(new BigDecimal(heat));
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }

                    //获取笔记本图片的地址
                    String picture = li.css("li > .pic > img", ".src").get();
                    zolNotebook.setPictureUrl(picture);

                    zolNotebooks.add(zolNotebook);

                    count++;
                }
                page.putField("zolNotebookList", zolNotebooks);
            }

            redisService.setCacheObject(REPTILE_ZOL_PHONE_COUNT, count);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            redisService.expire(REPTILE_ZOL_PHONE_COUNT, 3, TimeUnit.HOURS);
        }

    }

    @Override
    public Site getSite() {
        return Site.me()
                //.addHeader(headerKey, headerValue)
                .setCharset("GBK")//设置字符编码
                .setTimeOut(3000)//设置超时时间
                .setRetrySleepTime(100)//设置重试间隔时间
                .setCycleRetryTimes(10)//设置重试次数
                .setSleepTime(1)//设置两个页面之间的间隔时间
                ;
    }
}
