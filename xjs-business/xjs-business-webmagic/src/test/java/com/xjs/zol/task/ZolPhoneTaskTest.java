package com.xjs.zol.task;

import com.xjs.XjsWebmagicApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-04-17
 */
@SpringBootTest(classes = XjsWebmagicApp.class)
class ZolPhoneTaskTest {

    @Autowired
    private ZolPhoneTask zolPhoneTask;

    @Test
    void reptileZolPhone() {
        Long aLong = zolPhoneTask.reptileZolPhone();
        System.out.println(aLong);
    }
}
