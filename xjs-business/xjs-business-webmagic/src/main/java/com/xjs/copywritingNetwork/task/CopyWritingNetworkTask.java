package com.xjs.copywritingNetwork.task;

import com.xjs.annotation.ReptileLog;
import com.xjs.common.util.HttpUtils;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;
import com.xjs.copywritingNetwork.service.CopyWritingNetworkService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.xjs.consts.RegexConst.NUMBER_REGEX;
import static com.xjs.consts.ReptileConst.COPY_WRITING_NETWORK_URL;

/**
 * 文案网爬虫任务   url:https://www.wenanwang.com/
 *
 * @author xiejs
 * @since 2022-02-16
 */
@Component
@Log4j2
public class CopyWritingNetworkTask {

    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private CopyWritingNetworkService copyWritingNetworkService;



    private static final Pattern pattern = Pattern.compile(NUMBER_REGEX);

    @ReptileLog(name = "文案网", url = COPY_WRITING_NETWORK_URL)
    public Long reptileCopyWriting() {
        //定义循环次数计时器
        Long count = 0L;

        try {

            String html = httpUtils.doGetHtml(COPY_WRITING_NETWORK_URL);

            Document document = Jsoup.parse(html);

            count = this.parseHtmlGetUrl(document, count);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return count;
    }

    /**
     * 解析html获取url
     *
     * @param document dom
     * @param count    循环次数
     * @return 返回循环次数
     */
    private Long parseHtmlGetUrl(Document document, Long count) {
        Elements zyzt = document.getElementsByClass("zyzt");

        Map<String, String> map = new HashMap<>();
        for (Element element : zyzt) {
            Elements a = element.select("a");
            for (Element elementA : a) {
                String text = elementA.text();
                String href = elementA.attr("href");
                map.put(text, href);

                //计数
                count++;
            }
        }

        return this.parseHtmlGetCopyWriting(map, count);

    }

    /**
     * 解析html获取文案内容并持久化
     *
     * @param map   存放了url和名称
     * @param count 循环次数
     * @return 返回循环次数
     */
    private Long parseHtmlGetCopyWriting(Map<String, String> map, Long count) {
        ArrayList<CopyWritingNetwork> copyWritingNetworks = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String url = entry.getValue();
            String html = httpUtils.doGetHtml(url);
            Document document = Jsoup.parse(html);
            Elements a = document.select(".newslist li h5 a");
            for (Element element : a) {

                String href = element.attr("href");
                String newUrl = COPY_WRITING_NETWORK_URL + href;

                String cw = httpUtils.doGetHtml(newUrl);
                Document cwDocument = Jsoup.parse(cw);
                Elements h1 = cwDocument.select(".newsview > h1");
                String theme = null;
                for (Element cH1 : h1) {
                    theme = cH1.text();
                }
                Elements ps = cwDocument.select(".content > p");
                for (Element p : ps) {
                    CopyWritingNetwork copyWritingNetwork = new CopyWritingNetwork();
                    copyWritingNetwork.setTheme(theme);
                    copyWritingNetwork.setContent(p.text());
                    copyWritingNetwork.setType(entry.getKey());
                    String content = copyWritingNetwork.getContent();
                    boolean matches = pattern.matcher(content).matches();
                    if (StringUtils.isNotEmpty(content) && !matches) {

                        //过滤数据
                        String cont = filterContent(content);
                        copyWritingNetwork.setContent(cont);

                        copyWritingNetworks.add(copyWritingNetwork);
                    }

                    //计数
                    count++;
                }
            }
        }

        copyWritingNetworkService.saveBatch(copyWritingNetworks, 20);

        int i = copyWritingNetworkService.deleteRepeatData();
        log.info("删除文案网数据重复数：" + i);

        return count;
    }

    /**
     * 过滤数据
     *
     * @param oldStr 原始数据
     * @return newStr
     */
    private String filterContent(String oldStr) {
        try {
            char index0 = oldStr.charAt(0);
            char index1 = oldStr.charAt(1);
            char index2 = oldStr.charAt(2);
            char index3 = oldStr.charAt(3);
            boolean matches0 = pattern.matcher(String.valueOf(index0)).matches();
            boolean matches1 = pattern.matcher(String.valueOf(index1)).matches();
            boolean matches2 = pattern.matcher(String.valueOf(index2)).matches();
            //  1、
            if (matches0 && index1 == '、') {
                return oldStr.substring(2);
            }
            // 15、
            if (matches0 && matches1 && index2 == '、') {
                return oldStr.substring(3);
            }
            //100、
            if (matches0 && matches1 && matches2 && index3 == '、') {
                return oldStr.substring(4);
            }
            //1.
            if (matches0 && index1 == '.') {
                return oldStr.substring(2);
            }
            //13.
            if (matches0 && matches1 && index2 == '.') {
                return oldStr.substring(3);
            }
            //100.
            if (matches0 && matches1 && matches2 && index3 == '.') {
                return oldStr.substring(4);
            }
        } catch (Exception e) {
            log.error(e.getMessage()+"===="+oldStr);
            return oldStr;
        }
        return oldStr;
    }

}
