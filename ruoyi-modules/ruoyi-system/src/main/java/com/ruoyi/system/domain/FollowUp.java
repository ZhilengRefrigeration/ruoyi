package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 跟进模块-客户跟进记录对象 f_follow_up
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
@Data
@Table("f_follow_up")
public class FollowUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 跟进日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "跟进日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date followUpDate;

    /** 跟进方式 */
    @Excel(name = "跟进方式")
    private String followUpMethod;

    /** 跟进结果 */
    @Excel(name = "跟进结果")
    private String followResult;

    /** 意向级别 */
    @Excel(name = "意向级别")
    private String intentionLevel;

    /** 本次跟进记录 */
    @Excel(name = "本次跟进记录")
    private String followUpRecord;

    /** 下次跟进备注 */
    @Excel(name = "下次跟进备注")
    private String nextFollowUpRecord;

    /** 下次跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下次跟进时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nextFollowUpTime;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到店时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalTime;

    /** 战败原因 */
    @Excel(name = "战败原因")
    private String defeatReasons;
    @Excel(name = "预约状态")
    private String makerStatus;
    @Excel(name = "跟进类型（销售回访，潜客跟进）")
    private String followType;
    @Excel(name = "跟进目的")
    private String objective;

}
