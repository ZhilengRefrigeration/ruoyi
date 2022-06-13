package com.xjs.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * 生成随机工具类
 *
 * @author xiejs
 * @since 2022-06-13
 */
public class RandomUtils {

    private static String[] zm = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    /**
     * 获取随机字母
     * @return
     */
    public static String randomZm() {
        int i = RandomUtil.randomInt(0, 25);
        return zm[i];
    }

}
