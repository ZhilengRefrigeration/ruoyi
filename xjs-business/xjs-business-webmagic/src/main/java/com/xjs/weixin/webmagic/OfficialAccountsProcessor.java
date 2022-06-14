package com.xjs.weixin.webmagic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_OFFICIAL_COUNT;
import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_OFFICIAL_NAME;
import static com.xjs.consts.ReptileConst.WEIXIN_SOUGOU_URL;

/**
 * 微信公众号爬虫
 *
 * @author xiejs
 * @since 2022-06-13
 */
@Component
public class OfficialAccountsProcessor implements PageProcessor {

    @Autowired
    private RedisService redisService;

    /**
     * 请求头key
     */
    private static final String headerKey = "User-Agent";
    /**
     * 请求头value
     */
    private static final String headerValue = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36";


    @Override
    public void process(Page page) {

        try {
            Integer count = redisService.getCacheObject(REPTILE_WEIXIN_OFFICIAL_COUNT);
            if (count == null) {
                count = 0;
            }

            List<Selectable> lis = page.getHtml().css(".news-box > .news-list2 > li").nodes();
            ArrayList<String> urls = new ArrayList<>();
            for (Selectable li : lis) {
                String href = li.css("dl > dd > a", "href").get();

                Object cacheObject = redisService.getCacheObject(REPTILE_WEIXIN_OFFICIAL_NAME);
                String str = (String) cacheObject;
                if (StringUtils.isNotEmpty(str)) {
                    String text = li.css(".txt-box > .tit > a > em", "text").get();
                    String textA = li.css(".txt-box > .tit > a", "text").get();
                    if (StringUtils.isNotEmpty(textA)) {
                        continue;
                    }

                    if (str.equals(text)) {

                        //只爬取当天的文章
                        String date = li.css("dl > dd > span > script").get();
                        Pattern pattern = Pattern.compile("'(.*?)'");
                        Matcher matcher = pattern.matcher(date);
                        while (matcher.find()) {
                            //拿到时间戳
                            String word = matcher.group(1);

                            DateTime dateTime = DateUtil.date(Long.parseLong(word) * 1000);
                            String dateStr = dateTime.toDateStr();

                            String nowDateStr = DateUtil.formatDate(new Date());

                            if (dateStr.equals(nowDateStr)) {
                                urls.add(WEIXIN_SOUGOU_URL + href);
                            }
                        }
                    }
                }
                redisService.deleteObject(REPTILE_WEIXIN_OFFICIAL_NAME);

            }

            page.addTargetRequests(urls);

            String js = page.getHtml().get();

            //解析js中的url地址以及拼接
            if (js.contains("window.location.replace(url)")) {
                String function = js.substring(js.indexOf("{") + 1, js.indexOf("}"));
                //System.out.println("function="+function);

                //正则匹配 ' ' 里面的内容
                Pattern pattern = Pattern.compile("'(.*?)'");
                Matcher matcher = pattern.matcher(function);
                StringBuilder stringBuilder = new StringBuilder();
                while (matcher.find()) {
                    String word = matcher.group(1);
                    stringBuilder.append(word);
                }
                page.addTargetRequests(Collections.singletonList(stringBuilder.toString()));
            }

            //获取图片url
            List<String> linkList = page.getHtml().css("img", "data-src").all();
            //去空
            linkList.removeIf(StringUtils::isBlank);

            //获取标题
            String title = page.getHtml().css("#activity-name", "text").get();

            if (StringUtils.isNotEmpty(title)) {
                page.putField("title", title);
            }
            if (CollUtil.isNotEmpty(linkList)) {
                page.putField("linkList", linkList);
            }

            count = linkList.size();

            redisService.setCacheObject(REPTILE_WEIXIN_OFFICIAL_COUNT, count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisService.expire(REPTILE_WEIXIN_OFFICIAL_COUNT, 3, TimeUnit.HOURS);
            redisService.expire(REPTILE_WEIXIN_OFFICIAL_NAME, 3, TimeUnit.HOURS);
        }

    }

    @Override
    public Site getSite() {
        return Site.me()
                //.addHeader(headerKey, headerValue)
                .addHeader(headerKey, headerValue)
                .setCharset("utf8")//设置字符编码
                .setTimeOut(2000)//设置超时时间
                .setRetrySleepTime(100)//设置重试间隔时间
                .setCycleRetryTimes(10)//设置重试次数
                .setSleepTime(1)//设置两个页面之间的间隔时间
                ;
    }
}
