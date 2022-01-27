package httpcilent;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author xiejs
 * @since 2022-01-26
 */
public class HttpGetTest {


    public static void main(String[] args) throws URISyntaxException {
        //1、打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2、输入网址


        URIBuilder uriBuilder = new URIBuilder("http://www.itcast.cn");
        uriBuilder.setParameter("keys", "java");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        HttpPost httpPost = new HttpPost(uriBuilder.build());


        CloseableHttpResponse response = null;
        //3、回车，发起请求，返回响应，使用httpClient对象发起请求
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity, "utf8");
            System.out.println(data.length());


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //
    }

}
