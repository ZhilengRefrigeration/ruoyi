package com.xjs.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

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


    @Test
    public void test2() {
        Date date = DateUtil.parse("2022-01-01 23:59:59").toJdkDate();
        long between = DateUtil
                .between(new Date(), date, DateUnit.DAY);
        System.out.println(between);
    }

}