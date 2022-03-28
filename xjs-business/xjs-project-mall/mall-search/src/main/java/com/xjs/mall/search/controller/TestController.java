package com.xjs.mall.search.controller;

import com.xjs.mall.other.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.xjs.mall.search.config.ElasticsearchConfig.COMMON_OPTIONS;

/**
 * @author xiejs
 * @since 2022-03-26
 */
@RequestMapping("test")
@Api(tags = "test")
@RestController
public class TestController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @GetMapping
    @ApiOperation("测试")
    public R test() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.source("userName", "zs", "age", 18);

        IndexResponse index = restHighLevelClient.index(indexRequest, COMMON_OPTIONS);

        System.out.println(index.toString());

        return R.ok();
    }
}
