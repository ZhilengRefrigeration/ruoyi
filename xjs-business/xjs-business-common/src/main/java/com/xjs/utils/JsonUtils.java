package com.xjs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 * @author xiejs
 * @since 2022-02-24
 */
public class JsonUtils {

    /**
     * 解析jsonp
     * @param jsonp 一种json文本格式
     * @return JSONObject
     */
    public static JSONObject parseJsonp(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex+1, endIndex);
        return JSON.parseObject(json);
    }
}
