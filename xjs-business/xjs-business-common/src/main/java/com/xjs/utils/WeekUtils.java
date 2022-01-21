package com.xjs.utils;

/**
 * 星期处理工具类
 * @author xiejs
 * @since 2022-01-21
 */
public class WeekUtils {

    public static String weekConvert(String week) {
        switch (week) {
            case "1":
                week="周一";
                break;
            case "2":
                week="周二";
                break;
            case "3":
                week="周三";
                break;
            case "4":
                week="周四";
                break;
            case "5":
                week="周五";
                break;
            case "6":
                week="周六";
                break;
            case "7":
                week="周日";
                break;
        }
        return week;
    }

}
