package com.xjs.zol.task;

import com.xjs.XjsWebmagicApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiejs
 * @since 2022-04-17
 */
@SpringBootTest(classes = XjsWebmagicApp.class)
class ZolTaskTest {

    @Autowired
    private ZolTask zolTask;

    @Test
    void reptileZolPhone() {
        Long aLong = zolTask.reptileZol();
        System.out.println(aLong);
    }
}
