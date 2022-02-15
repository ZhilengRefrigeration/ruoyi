package com.xjs.common.util;

import com.ruoyi.common.core.constant.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 爬虫工具
 *
 * @author xiejs
 * @since 2022-02-15
 */
@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     *
     * @param url 地址
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //设置httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");

        //设置请求信息
        httpGet.setConfig(this.getConfig());



        //使用httpClient发起请求，获取响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //解析响应，获取结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SUCCESS) {
                //判断响应体entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    return EntityUtils.toString(response.getEntity(),"utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }



    /**
     * 根据请求地址下载图片
     *
     * @param url 地址
     * @return 图片名称
     */
    public String doGetImage(String url) {
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //设置httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        //使用httpClient发起请求，获取响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //解析响应，获取结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SUCCESS) {
                //判断响应体entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    //获取图片后缀
                    String extName = url.substring(url.lastIndexOf("."));

                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString()+extName;

                    //下载图片
                    OutputStream outputStream =new FileOutputStream("D:\\Dev\\WebCrawler\\jd\\image"+picName);
                    response.getEntity().writeTo(outputStream);

                    //返回图片名称
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }


    /**
     * 设置请求配置
     * @return RequestConfig
     */
    private RequestConfig getConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(2000)//创建连接的最长时间
                .setConnectionRequestTimeout(1000)//获取连接的最长时间
                .setSocketTimeout(10000)//数据传输的最长时间
                .build();
    }
}
