package httpcilent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @author xiejs
 * @since 2022-01-27
 */
public class JsoupFirstTest {


    @Test
    public void testUrl() throws IOException {

        //解析url地址  第一个参数访问的url，第二个参数是超时时间
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"),
                1000);

        //使用标签选择器
        String text = document.getElementsByTag("title").first().text();

        System.out.println(text);


    }

    @Test
    public void testDom() throws IOException {

        Document document = Jsoup.parse(new URL("http://www.itcast.cn"),
                1000);

        Elements a_name = document.getElementsByClass("a_name");

        System.out.println(a_name.first().text());

    }


    @Test
    public void testData() throws IOException {
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"),
                1000);


    }
































}
