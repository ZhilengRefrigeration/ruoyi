package com.bwie.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户类型对象 tb_user_role
 * 
 * @author xs
 * @date 2023-01-15
 */
public class TbUserRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long userRoleId;

    /** 用户角色类型 */
    @Excel(name = "用户角色类型")
    private String userRoleName;

    /** 用户角色有哪些功能 */
    @Excel(name = "用户角色有哪些功能")
    private String roleFunction;

    public void setUserRoleId(Long userRoleId) 
    {
        this.userRoleId = userRoleId;
    }

    public Long getUserRoleId() 
    {
        return userRoleId;
    }
    public void setUserRoleName(String userRoleName) 
    {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleName() 
    {
        return userRoleName;
    }
    public void setRoleFunction(String roleFunction) 
    {
        this.roleFunction = roleFunction;
    }

    public String getRoleFunction() 
    {
        return roleFunction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userRoleId", getUserRoleId())
            .append("userRoleName", getUserRoleName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("roleFunction", getRoleFunction())
            .toString();
    }
}
