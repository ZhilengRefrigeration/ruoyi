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
        long between = DateUtil
                .between(new Date(), DateUtil.parseDate("2021-12-31 23:59:59").toJdkDate(), DateUnit.DAY);
        System.out.println(between);
    }

}