package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 微信用户小程序二维码对象 user_wx_aqr_code
 *
 * @author ruoyi
 * @date 2022-10-20
 */
@Data
@Table("user_wx_aqr_code")
public class UserWxAqrCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 删除表示 */
    @Excel(name = "删除表示")
    private Long isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 修改人 */
    @Excel(name = "修改人")
    private String modifiedBy;

    /** 最新更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最新更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 参数 */
    @Excel(name = "参数")
    private String scene;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String codeImgUrl;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 业务分类枚举 */
    @Excel(name = "业务分类枚举")
    private String busType;

    /** 页面路径 */
    @Excel(name = "页面路径")
    private String page;

    /** 宽度 */
    @Excel(name = "宽度")
    private Integer width;

    /** 用途说明 */
    @Excel(name = "用途说明")
    private String useDesc;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNo;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    @Excel(name = "赛会参赛人员ID")
    private Long competitionMembersId;
    @Excel(name = "赛会参赛球队ID")
    private Long competitionOfTeamId;

}
