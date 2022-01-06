package com.xjs.servicemonitor.service;

import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.JvmSpecInfo;
import com.xjs.XjsMonitorApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @desc
 * @create 2022-01-02
 */
@SpringBootTest(classes = XjsMonitorApp.class)
class JvmMonitorServiceTest {

    @Autowired
    JvmMonitorService jvmMonitorService;


    @Test
    void getJvmSpecInfo() {
        JvmSpecInfo jvmSpecInfo = jvmMonitorService.getJvmSpecInfo();
        System.out.println(jvmSpecInfo);

    }

    @Test
    void getJvmInfo() {
        JvmInfo jvmInfo = jvmMonitorService.getJvmInfo();
        System.out.println(jvmInfo);
    }

    @Test
    void getJavaRuntimeInfo() {
        JavaRuntimeInfo javaRuntimeInfo = jvmMonitorService.getJavaRuntimeInfo();
        System.out.println(javaRuntimeInfo);
    }
}