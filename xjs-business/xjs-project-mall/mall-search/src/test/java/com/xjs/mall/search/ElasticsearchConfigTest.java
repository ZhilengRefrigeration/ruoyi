package com.xjs.mall.search;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

import static com.xjs.mall.search.config.ElasticsearchConfig.COMMON_OPTIONS;

/**
 * @author xiejs
 * @since 2022-03-25
 */
@SpringBootTest(classes = MallSearchApp.class)
class ElasticsearchConfigTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Test
    /**
     * 测试存储数据到es
     */
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.source("userName", "zs", "age", 18);

        IndexResponse index = restHighLevelClient.index(indexRequest, COMMON_OPTIONS);

        System.out.println(index.toString());


    }

}
