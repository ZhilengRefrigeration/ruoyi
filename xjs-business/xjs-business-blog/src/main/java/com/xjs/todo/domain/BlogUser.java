package com.xjs.todo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客用户实体
 *
 * @author xiejs
 * @since 2022-01-27
 */
@Data
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 用户密码
     */
    @Excel(name = "用户密码")
    private String userPassword;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String userNick;

    /**
     * 用户头像
     */
    @Excel(name = "用户头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private Long phoneNumber;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 用户性别 1：男 2：女
     */
    @Excel(name = "用户性别", readConverterExp = "1=男,2=女")
    private Integer sex;

    /**
     * 登录次数
     */
    @Excel(name = "登录次数")
    private Long loginCount;

    /**
     * 最后登录Ip
     */
    @Excel(name = "最后登录Ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
