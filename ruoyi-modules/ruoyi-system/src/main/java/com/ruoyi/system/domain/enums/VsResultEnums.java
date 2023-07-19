package com.ruoyi.system.domain.enums;

import org.springframework.util.StringUtils;

public enum VsResultEnums {
    win("win", "胜"),
    flat("flat","平"),
    fail("fail", "负");

    private String code;

    private String message;

    VsResultEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        for(VsResultEnums roles:VsResultEnums.values()){
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
