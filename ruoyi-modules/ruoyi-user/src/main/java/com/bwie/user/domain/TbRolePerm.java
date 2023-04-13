package com.bwie.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 角色权限对象 tb_role_perm
 * 
 * @author xs
 * @date 2023-01-15
 */
public class TbRolePerm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色权限id */
    private Long rolePermId;

    /** 角色ID */
    @Excel(name = "角色ID")
    private Long roleId;

    /** 权限ID */
    @Excel(name = "权限ID")
    private Long permId;

    /** 删除状态0：未删除1：已删除 */
    @Excel(name = "删除状态0：未删除1：已删除")
    private Integer deleted;

    public void setRolePermId(Long rolePermId) 
    {
        this.rolePermId = rolePermId;
    }

    public Long getRolePermId() 
    {
        return rolePermId;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setPermId(Long permId) 
    {
        this.permId = permId;
    }

    public Long getPermId() 
    {
        return permId;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rolePermId", getRolePermId())
            .append("roleId", getRoleId())
            .append("permId", getPermId())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
