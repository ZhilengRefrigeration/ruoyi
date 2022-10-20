package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 微信用户对象 user_info
 * 
 * @author 吴一博
 * @date 2022-08-30
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 删除 */
    @Excel(name = "删除")
    private Integer isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 登录名称 */
    @Excel(name = "登录名称")
    private String loginName;

    /** 密码 */
    @Excel(name = "密码")
    private String passWord;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 用户编码(小程序平台OPENID) */
    @Excel(name = "用户编码(小程序平台OPENID)")
    private String openid;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String telephone;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 身高 */
    @Excel(name = "身高")
    private Long height;

    /** 体重 */
    @Excel(name = "体重")
    private BigDecimal weight;

    /** 球队位置 */
    @Excel(name = "球队位置")
    private String teamPosition;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 状态 */
    @Excel(name = "状态")
    private String enabled;

    /** 微信多平台唯一ID */
    @Excel(name = "微信多平台唯一ID")
    private String unionid;

    /** 公众号平台的openId */
    @Excel(name = "公众号平台的openId")
    private String officialAccountOpenid;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setModifiedBy(String modifiedBy) 
    {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() 
    {
        return modifiedBy;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) 
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastUpdatedTime() 
    {
        return lastUpdatedTime;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setPassWord(String passWord) 
    {
        this.passWord = passWord;
    }

    public String getPassWord() 
    {
        return passWord;
    }
    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setHeight(Long height) 
    {
        this.height = height;
    }

    public Long getHeight() 
    {
        return height;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setTeamPosition(String teamPosition) 
    {
        this.teamPosition = teamPosition;
    }

    public String getTeamPosition() 
    {
        return teamPosition;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setEnabled(String enabled) 
    {
        this.enabled = enabled;
    }

    public String getEnabled() 
    {
        return enabled;
    }
    public void setUnionid(String unionid) 
    {
        this.unionid = unionid;
    }

    public String getUnionid() 
    {
        return unionid;
    }
    public void setOfficialAccountOpenid(String officialAccountOpenid) 
    {
        this.officialAccountOpenid = officialAccountOpenid;
    }

    public String getOfficialAccountOpenid() 
    {
        return officialAccountOpenid;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("isDeleted", getIsDeleted())
            .append("createdTime", getCreatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("loginName", getLoginName())
            .append("passWord", getPassWord())
            .append("role", getRole())
            .append("openid", getOpenid())
            .append("avatar", getAvatar())
            .append("gender", getGender())
            .append("userName", getUserName())
            .append("telephone", getTelephone())
            .append("birthday", getBirthday())
            .append("height", getHeight())
            .append("weight", getWeight())
            .append("teamPosition", getTeamPosition())
            .append("tag", getTag())
            .append("enabled", getEnabled())
            .append("unionid", getUnionid())
            .append("officialAccountOpenid", getOfficialAccountOpenid())
            .append("realName", getRealName())
            .toString();
    }
}
