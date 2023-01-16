package com.ruoyi.system.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 苏海龙
 * @version 1.0
 * @description: TODO
 * @date 2023/1/14 8:05
 */

@Data
public class PassWordCode implements Serializable {
    private Integer userId;
    private String oldPassword;
    private String newPassword;
}
