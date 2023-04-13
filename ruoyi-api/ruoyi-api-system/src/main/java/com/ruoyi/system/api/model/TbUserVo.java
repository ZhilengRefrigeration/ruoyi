package com.ruoyi.system.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 苏海龙
 * @version 1.0
 * @description: TODO
 * @date 2023/1/10 15:03
 */

@Data
public class TbUserVo extends BaseEntity {
    private Long userId;
    private String token;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 身份证(只有管理员能看到)
     */
    private String userCard;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 账号金额(元)
     */
    private Double userPrice;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 实名状态(0,未实名1已实名)
     */
    private Integer realnameState;

    /**
     * 信誉积分
     */
    private Integer honorIntegral;

}
