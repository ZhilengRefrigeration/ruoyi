package com.xjs.mall.search.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearcn配置类
 *
 * @author xiejs
 * @since 2022-03-25
 */
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchConfig {

    /**
     * es的ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 协议
     */
    private String scheme;


    public static RequestOptions COMMON_OPTIONS;

    static {
        COMMON_OPTIONS = RequestOptions.DEFAULT.toBuilder().build();
    }


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(ip, port, scheme));
        return new RestHighLevelClient(builder);
    }

}
