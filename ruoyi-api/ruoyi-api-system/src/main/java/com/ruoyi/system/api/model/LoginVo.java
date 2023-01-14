package com.ruoyi.system.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 苏海龙
 * @version 1.0
 * @description: TODO
 * @date 2023/1/10 20:57
 */
//登录的vo实体类
@Data
public class LoginVo implements Serializable {
    private String nickName;
    private String userPass;
    private String code;
    private String phone;
}
