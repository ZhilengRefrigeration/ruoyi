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

    /** 跟进记录 */
    @Excel(name = "跟进记录")
    private String followUpRecord;

    /** 再次预约到店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "再次预约到店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date preToStoreDate;

    /** 级别 */
    @Excel(name = "级别")
    private String followLevel;
    @Excel(name = "跟进结果")
    private String followResult;
    @Excel(name = "跟进方式")
    private String followUpMethod;

}
