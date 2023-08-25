package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 比赛参与人员对象 competition_members
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
public class CompetitionMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleCode;

    /** 比赛ID */
    @Excel(name = "比赛ID")
    private Long competitionId;

    /** 参赛球队id */
    @Excel(name = "参赛球队id")
    private Long competitionTeamId;

    /** 比赛得分 */
    @Excel(name = "比赛得分")
    private Integer score;

    /** 总罚球 */
    @Excel(name = "总罚球")
    private Integer penalty;

    /** 2分球 */
    @Excel(name = "2分球")
    private Integer twoPoints;

    /** 3分球 */
    @Excel(name = "3分球")
    private Integer threePoints;

    /** 总犯规 */
    @Excel(name = "总犯规")
    private Integer breaks;

    /** 总篮板球 */
    @Excel(name = "总篮板球")
    private Integer rebound;

    /** 总盖帽 */
    @Excel(name = "总盖帽")
    private Integer block;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 人员类型：0=球员；1=队长 */
    @Excel(name = "人员类型：0=球员；1=队长")
    private Integer userType;

    /** competition_of_team主键ID */
    @Excel(name = "competition_of_team主键ID")
    private Long competitionOfTeamId;

    /** 比赛性质（0=约战；1=赛事） */
    @Excel(name = "比赛性质", readConverterExp = "0==约战；1=赛事")
    private Integer competitionNature;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNumber;

    /** 证件类型（身份证） */
    @Excel(name = "证件类型", readConverterExp = "身=份证")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCardNo;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactsTel;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系人区号 */
    @Excel(name = "联系人区号")
    private String contactsAreaCode;

    /** 个人照片（最新） */
    @Excel(name = "个人照片", readConverterExp = "最=新")
    private String personalPhoto;
    @Excel(name = "是否隐藏头像")
    private Boolean isHideAvatar;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer isFirstLaunch;
    @Excel(name = "位置")
    private String teamPosition;
    @Excel(name = "身高")
    private BigDecimal height;
    @Excel(name = "体重")
    private BigDecimal weight;

}
