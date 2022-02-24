package com.xjs.ai.factory.impl;

import com.xjs.XjsOpenApiApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-02-24
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class BaiduAssociationFactoryTest {

    @Autowired
    BaiduAssociationFactory baiduAssociationFactory;

    @Test
    void getData() {
        List<String> xx = baiduAssociationFactory.getData("哈哈");

        for (String s : xx) {
            System.out.println(s);
        }
    }
}