package com.bwie.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 权限对象 tb_perm
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public class TbPerm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long permId;

    /** 权限名称 */
    @Excel(name = "权限名称")
    private String permName;

    /** 权限编码 */
    @Excel(name = "权限编码")
    private String permCode;

    /** 删除状态0：未删除1：已删除 */
    @Excel(name = "删除状态0：未删除1：已删除")
    private Integer deleted;

    public void setPermId(Long permId) 
    {
        this.permId = permId;
    }

    public Long getPermId() 
    {
        return permId;
    }
    public void setPermName(String permName) 
    {
        this.permName = permName;
    }

    public String getPermName() 
    {
        return permName;
    }
    public void setPermCode(String permCode) 
    {
        this.permCode = permCode;
    }

    public String getPermCode() 
    {
        return permCode;
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
            .append("permId", getPermId())
            .append("permName", getPermName())
            .append("permCode", getPermCode())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
