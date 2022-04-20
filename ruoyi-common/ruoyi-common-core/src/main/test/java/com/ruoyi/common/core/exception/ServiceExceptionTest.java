package com.ruoyi.common.core.exception;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceExceptionTest {

    @BeforeEach
    void setUp() {
        System.out.println("up");
    }

    @AfterEach
    void tearDown() {
        System.out.println("down");
    }

    @Test
    void testThirdTest() {
        GlobalException globalException2 = new GlobalException("");
        globalException2.setDetailMessage("");
        throw globalException2;
    }

    @Test
    void testThirdTest2() {
        GlobalException globalException2 = new GlobalException();
        System.out.println(globalException2.getMessage());
        System.out.println(globalException2.getDetailMessage());
        throw globalException2.setDetailMessage("");
    }
}