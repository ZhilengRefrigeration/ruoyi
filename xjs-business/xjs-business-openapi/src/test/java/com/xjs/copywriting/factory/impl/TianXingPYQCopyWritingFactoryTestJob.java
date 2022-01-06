package com.xjs.copywriting.factory.impl;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.XjsOpenApiApp;
import com.xjs.copywriting.domain.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class TianXingPYQCopyWritingFactoryTestJob {

    @Autowired(required = false)
    TianXingPYQCopyWritingFactory tianXingCopyWritingFactory;

    @Autowired
    RedisService redisService;

    @Test
    void productCopyWriting() {
        tianXingCopyWritingFactory.productCopyWriting(new RequestBody());
    }


    @Test
    void testRedis() {
        redisService.dHashByKey("tranDict",
                "interface:1476499056043425794");
    }
}