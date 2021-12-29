package com.xjs.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-29
 */
class ChineseUtilTest {

    @Test
    public void test1() {
        boolean b = ChineseUtil.checkNameChese("爱喔爱");
        System.out.println(b);
    }

}