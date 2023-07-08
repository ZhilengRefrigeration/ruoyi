package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 比赛信息对象 competition
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Competition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 主队ID */
    @Excel(name = "主队ID")
    private Long mainTeamId;

    /** 主队名 */
    @Excel(name = "主队名")
    private String mainTeamName;
    @ApiModelProperty(value = "主队logo", required = false)
    private String mainTeamLogo;

    /** 客队ID */
    @Excel(name = "客队ID")
    private Long guestTeamId;
    @ApiModelProperty(value = "客队logo", required = false)
    private String guestTeamLogo;

    /** 客队名 */
    @Excel(name = "客队名")
    private String guestTeamName;

    /** 赛事编号 */
    @Excel(name = "赛事编号")
    private String competitionCode;

    /** 比赛名称 */
    @Excel(name = "比赛名称")
    private String competitionName;

    /** 是否指定对手 */
    @Excel(name = "是否指定对手")
    private Integer designated;

    /** 比赛类型,1,2,3,4,5,6 */
    @Excel(name = "比赛类型,1,2,3,4,5,6")
    private Long competitionType;

    /** 比赛时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionTime;

    /** 球场ID */
    @Excel(name = "球场ID")
    private Long buildingId;

    /** 球场名称 */
    @Excel(name = "球场名称")
    private String buildingName;

    /** 比赛地址 */
    @Excel(name = "比赛地址")
    private String competitionAddress;

    /** 发起人ID */
    @Excel(name = "发起人ID")
    private Long founder;

    /** 比赛状态 */
    @Excel(name = "比赛状态")
    private Integer status;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String cityCode;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cityName;

    /** 最大参与人数 */
    @Excel(name = "最大参与人数")
    private Long maxPlayer;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 比赛性质（0=约战；1=赛事） */
    @Excel(name = "比赛性质", readConverterExp = "0==约战；1=赛事")
    private Integer competitionNature;

    /** 报名开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollBeginTime;

    /** 报名结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollEndTime;

    /** 赛事联系人 */
    @Excel(name = "赛事联系人")
    private String contacts;

    /** 赛事联系人电话区号 */
    @Excel(name = "赛事联系人电话区号")
    private String contactsAreaCode;

    /** 赛事联系人电话 */
    @Excel(name = "赛事联系人电话")
    private String contactsTel;

    /** 比赛开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionBeginTime;

    /** 比赛结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionEndTime;

    /** 主办方 */
    @Excel(name = "主办方")
    private String organizer;

    /** 承办方 */
    @Excel(name = "承办方")
    private String undertake;

    /** 赛会背景图 */
    @Excel(name = "赛会背景图")
    private String competitionBackImg;

    /** 创建人userId */
    @Excel(name = "创建人userId")
    private Long createdId;

    /** 审核状态0=待审核；1=已审核；-1=审核不过 */
    @Excel(name = "审核状态0=待审核；1=已审核；-1=审核不过")
    private Long auditStatus;

    /** 身高隐藏  0不隐藏 1=隐藏 */
    @Excel(name = "身高隐藏  0不隐藏 1=隐藏")
    private Integer heightHide;

    /** 赞助商 */
    @Excel(name = "赞助商")
    private String sponsor;

}
