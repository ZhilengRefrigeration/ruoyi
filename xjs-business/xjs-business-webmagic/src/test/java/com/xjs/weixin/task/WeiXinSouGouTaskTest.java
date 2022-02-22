package com.xjs.weixin.task;

import com.xjs.XjsWebmagicApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-02-22
 */
@SpringBootTest(classes = XjsWebmagicApp.class)
class WeiXinSouGouTaskTest {

    @Autowired
    WeiXinSouGouTask task;

    @Test
    void reptileWeiXinSouGou() {

        Long aLong = task.reptileWeiXinSouGou();
        System.out.println(aLong);
    }
}