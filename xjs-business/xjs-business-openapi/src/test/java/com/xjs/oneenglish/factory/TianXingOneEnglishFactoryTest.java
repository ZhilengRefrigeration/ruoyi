package com.xjs.oneenglish.factory;

import com.xjs.XjsOpenApiApp;
import com.xjs.config.TianXingProperties;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-31
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class TianXingOneEnglishFactoryTest {

    @Autowired
    TianXingOneEnglishFactory tianXingOneEnglishFactory;

    @Autowired
    TianXingProperties tianXingProperties;

    @Test
    void getOneEnglish() {
        RequestBody requestBody = new RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        ApiEnglish oneEnglish = tianXingOneEnglishFactory.getOneEnglish(requestBody);
        System.out.println(oneEnglish);
    }
}