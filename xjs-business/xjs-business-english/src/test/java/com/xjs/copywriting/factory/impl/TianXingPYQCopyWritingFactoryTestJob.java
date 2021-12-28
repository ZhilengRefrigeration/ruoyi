package com.xjs.copywriting.factory.impl;

import com.xjs.XjsEnglishApp;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
@SpringBootTest(classes = XjsEnglishApp.class)
class TianXingPYQCopyWritingFactoryTestJob {

    @Autowired
    CopyWritingFactory tianXingCopyWritingFactory;

    @Test
    void productCopyWriting() {
        tianXingCopyWritingFactory.productCopyWriting(new RequestBody());
    }
}