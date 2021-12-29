package com.xjs.copywriting.factory.impl;

import com.xjs.XjsOpenApiApp;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
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

    @Autowired
    CopyWritingFactory tianXingCopyWritingFactory;

    @Test
    void productCopyWriting() {
        tianXingCopyWritingFactory.productCopyWriting(new RequestBody());
    }
}