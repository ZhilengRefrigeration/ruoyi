package com.xjs.config.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;

import java.util.List;

/**
 * 忽略某些空值
 * @author xiejs
 * @since 2022-06-21
 */
public class IgnoreNullValueFilter implements PropertyFilter {

    @Override
    public boolean apply(Object object, String name, Object value) {
        if (value instanceof List && ((List) value).size() == 0) {
            if ("children".equals(name)) {
                return false;
            }
        }
        return true;
    }
}
