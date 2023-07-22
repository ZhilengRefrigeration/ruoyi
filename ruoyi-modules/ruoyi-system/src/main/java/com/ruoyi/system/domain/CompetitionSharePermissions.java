package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会-权限分享对象 competition_share_permissions
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
@Data
public class CompetitionSharePermissions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userTel;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 状态(sys_data_status) */
    @Excel(name = "状态(sys_data_status)")
    private Long status;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 能操作的功能 */
    @Excel(name = "能操作的功能")
    private String canSetType;
}
