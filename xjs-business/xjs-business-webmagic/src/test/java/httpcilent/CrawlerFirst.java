package httpcilent;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xiejs
 * @since 2022-01-26
 */
public class CrawlerFirst {

    public static void main(String[] args) throws IOException {
        //1、打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2、输入网址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        //3、回车，发起请求，返回响应，使用httpClient对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //4、解析响应，获取数据
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity, "utf8");
            System.out.println(data);
        }

    }

}
