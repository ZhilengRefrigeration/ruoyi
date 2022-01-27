package httpcilent;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author xiejs
 * @since 2022-01-26
 */
public class HttpClientPoolTest {


    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

        //使用连接池管理器发起请求
        


    }


    private static void doGet(PoolingHttpClientConnectionManager clientConnectionManager) throws URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager).build();
        URIBuilder uriBuilder = new URIBuilder("http://www.itcast.cn");
        uriBuilder.setParameter("keys", "java");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

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

        }
    }


}
