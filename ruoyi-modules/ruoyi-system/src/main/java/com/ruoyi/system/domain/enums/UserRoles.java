package com.ruoyi.system.domain.enums;

import org.springframework.util.StringUtils;

/**
 * Created by jackma on 2020/1/17.
 */
public enum UserRoles {
    ADMIN("admin", "管理员"),
    CURATOR("curator", "球馆负责人"),
    customer("customer", "普通用户");

    private String code;

    private String message;

    UserRoles(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        for(UserRoles roles:UserRoles.values()){
            if(code.equals(roles.code())){
                return roles.message();
            }
        }
        return null;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
