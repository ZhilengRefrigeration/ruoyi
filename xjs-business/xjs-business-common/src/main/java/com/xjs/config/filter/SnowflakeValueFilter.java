package com.xjs.config.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 解决mp雪花算法前端精度丢失
 * @author xiejs
 * @since 2022-06-21
 */
public class SnowflakeValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        if ((StringUtils.endsWith(name, "Id") || StringUtils.equals(name, "id")) && value != null && value.getClass() == Long.class) {
            return String.valueOf(value);
        }
        return value;
    }
}
