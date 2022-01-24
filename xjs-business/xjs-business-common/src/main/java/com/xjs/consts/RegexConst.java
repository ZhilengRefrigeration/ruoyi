package com.xjs.consts;

/**
 * 正则表达式常量
 *
 * @author xiejs
 * @since 2022-01-18
 */
public class RegexConst {
    /**
     * 手机号码正则
     */
    public static final String MOBILE_REGEX = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    /**
     * 18位身份证正则
     */
    public static final String IDCARD_18_REGEX = "^([1-6][1-9]|50)\\d{4}(18|19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";


    /**
     * 15位身份证正则
     */
    public static final String IDCARD_15_REGEX = "^([1-6][1-9]|50)\\d{4}\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}$";

    /**
     * ip地址v4、v6正则
     */
    public static final String IP_REGEX ="^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
}
