package com.ruoyi.cache.domain;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.lang.Mirror;

public class BaseCache {
	
	/**
	 * POJO转换成Map
	 * @return
	 */
	public Map<String,String> toMap(){
		Mirror<?> mirror = Mirror.me(getClass());
		Field[] flds = mirror.getFields();
		Map<String,String> pojoMap=new HashMap<String,String>();
		for (Field fld : flds) {
			Object v = mirror.getValue(this, fld);
            if (null == v) {//等与null就不生成map
                continue;
            } else if(fld.isAnnotationPresent(Column.class)){
            	String cv = fld.getAnnotation(Column.class).value();
            	pojoMap.put(cv, v.toString());
            } else{
            	pojoMap.put(fld.getName(), v.toString());
            }
		}
		return pojoMap;
	}

}
