package com.xjs.copywriting.factory.impl;

import com.xjs.XjsOpenApiApp;
import com.xjs.copywriting.domain.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-02-14
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class LqDogDiaryCopyWritingFactoryTest {

    @Autowired
    private LqDogDiaryCopyWritingFactory lqDogDiaryCopyWritingFactory;

    @Test
    void productCopyWriting() {
        lqDogDiaryCopyWritingFactory.productCopyWriting(new RequestBody());
    }
}