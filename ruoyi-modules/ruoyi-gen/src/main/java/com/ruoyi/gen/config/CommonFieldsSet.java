package com.ruoyi.gen.config;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.reflect.ReflectUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 共通字段设定
 *
 * @author Alan Scipio
 * created on 2024/2/18
 */
public class CommonFieldsSet {

    private static final List<String> COMMON_FIELDS = new ArrayList<>();

    static {
        Field[] fields = ReflectUtils.getFieldsDeep(ExtBaseEntity.class);
        for (Field field : fields) {
            //跳过静态字段
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            //将驼峰命名转换为下划线命名
            String columnName = StringUtils.toUnderScoreCase(field.getName());
            COMMON_FIELDS.add(columnName);
        }

        //从属部门ID也相当于是共通字段
        COMMON_FIELDS.add("DEPT_ID");
        //备注字段默认都是共通字段
        COMMON_FIELDS.add("remark*");
    }

    public static boolean isCommonField(String columnName) {
        for (String commonField : COMMON_FIELDS) {
            //如果字段名相同则是共通字段,不区分大小写
            if (commonField.equalsIgnoreCase(columnName)) {
                return true;
            } else if (commonField.endsWith("*")) {
                //如果字段名以*结尾则是通配符,不区分大小写
                String prefix = commonField.substring(0, commonField.length() - 1);
                if (columnName.toLowerCase().startsWith(prefix.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getCommonFields() {
        return COMMON_FIELDS;
    }

}
