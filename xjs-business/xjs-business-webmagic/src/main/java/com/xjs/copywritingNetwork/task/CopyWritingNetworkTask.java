package com.xjs.copywritingNetwork.task;

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


    public static final String URL = "https://www.wenanwang.com/";

    private static final Pattern pattern = Pattern.compile(NUMBER_REGEX);

    public void reptileCopyWriting() {
        try {
            String html = httpUtils.doGetHtml(URL);

            Document document = Jsoup.parse(html);

            this.parseHtmlGetUrl(document);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 解析html获取url
     *
     * @param document
     */
    private void parseHtmlGetUrl(Document document) {
        Elements zyzt = document.getElementsByClass("zyzt");

        Map<String, String> map = new HashMap<>();
        for (Element element : zyzt) {
            Elements a = element.select("a");
            for (Element elementA : a) {
                String text = elementA.text();
                String href = elementA.attr("href");
                map.put(text, href);
            }
        }

        this.parseHtmlGetCopyWriting(map);

    }

    /**
     * 解析html获取文案内容并持久化
     *
     * @param map 存放了url和名称
     */
    private void parseHtmlGetCopyWriting(Map<String, String> map) {
        ArrayList<CopyWritingNetwork> copyWritingNetworks = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String url = entry.getValue();
            String html = httpUtils.doGetHtml(url);
            Document document = Jsoup.parse(html);
            Elements a = document.select(".newslist li h5 a");
            for (Element element : a) {

                String href = element.attr("href");
                String newUrl = URL + href;

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
                        copyWritingNetworks.add(copyWritingNetwork);
                    }
                }
            }
        }

        copyWritingNetworkService.saveBatch(copyWritingNetworks, 20);

        int i = copyWritingNetworkService.deleteRepeatData();
        log.info("删除文案网数据重复数：" + i);

    }


}
