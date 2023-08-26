package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信用户对象 user_info
 * 
 * @author 吴一博
 * @date 2022-08-30
 */
@Data
@Table("user_info")
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 删除 */
    @Excel(name = "删除")
    private Integer isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 登录名称 */
    @Excel(name = "登录名称")
    private String loginName;

    /** 密码 */
    @Excel(name = "密码")
    private String passWord;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 用户编码(小程序平台OPENID) */
    @Excel(name = "用户编码(小程序平台OPENID)")
    private String openid;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 性别 */
    @Excel(name = "性别")
    private Integer gender;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String telephone;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 身高 */
    @Excel(name = "身高")
    private Long height;

    /** 体重 */
    @Excel(name = "体重")
    private BigDecimal weight;

    /** 球队位置 */
    @Excel(name = "球队位置")
    private String teamPosition;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 状态 */
    @Excel(name = "状态")
    private Integer enabled;

    /** 微信多平台唯一ID */
    @Excel(name = "微信多平台唯一ID")
    private String unionid;

    /** 公众号平台的openId */
    @Excel(name = "公众号平台的openId")
    private String officialAccountOpenid;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

}
