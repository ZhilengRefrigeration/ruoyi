package com.pinn.publicPackage.encryption;

import cn.hutool.core.map.MapUtil;
import com.pinn.publicPackage.utils.AESMybatisPlusUtils;

import java.util.HashMap;

/**
 * plus插件数据安全保护
 */
public class MybatisPlusAES {
    public static void main(String[] args) {
        HashMap<String, String> dataSource = MapUtil.newHashMap();
        dataSource.put("url","jdbc:mysql://175.178.38.240:9033/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dataSource.put("username","nacos");
        dataSource.put("password","nacos2233");

        AESMybatisPlusUtils.encryptionDataSource(dataSource);
    }
}