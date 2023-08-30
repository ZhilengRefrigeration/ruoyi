package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 赛会-权限分享对象 competition_share_permissions
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
@Data
@TableName("competition_share_permissions")
public class CompetitionSharePermissions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type = IdType.AUTO)
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
