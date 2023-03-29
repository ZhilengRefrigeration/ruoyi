package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 公告接收者对象 t_bulletin_recive
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public class BulletinRecive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接收ID */
    private String reciveId;

    /** 接收员工ID */
    @Excel(name = "接收员工ID")
    private Long reciveUserId;
    /**
     * 接收部门ID
     */
    private Long reciveDeptId;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;

    /** 阅读次数 */
    @Excel(name = "阅读次数")
    private Long readNum;

    /** 员工ID */
    @Excel(name = "员工ID")
    private Long createUserId;

    /** 更新员工 */
    @Excel(name = "更新员工")
    private Long updateUserId;

    /** 公告ID */
    @Excel(name = "公告ID")
    private String bulletinId;

    /** A:已阅读，B:已删除 C:未阅读 */
    @Excel(name = "A:已阅读，B:已删除 C:未阅读")
    private String sts;
    
    

    public Long getReciveDeptId() {
		return reciveDeptId;
	}
	public void setReciveDeptId(Long reciveDeptId) {
		this.reciveDeptId = reciveDeptId;
	}
	/**
            *  设置 ReciveId
    * @param 接收ID
    */
    public void setReciveId(String reciveId) 
    {
        this.reciveId = reciveId;
    }
    /**
             *  获取 接收ID
     * @return
     */
    public String getReciveId() 
    {
        return reciveId;
    }
    /**
            *  设置 ReciveUserId
    * @param 接收员工ID
    */
    public void setReciveUserId(Long reciveUserId) 
    {
        this.reciveUserId = reciveUserId;
    }
    /**
             *  获取 接收员工ID
     * @return
     */
    public Long getReciveUserId() 
    {
        return reciveUserId;
    }
    /**
            *  设置 ReadTime
    * @param 阅读时间
    */
    public void setReadTime(Date readTime) 
    {
        this.readTime = readTime;
    }
    /**
             *  获取 阅读时间
     * @return
     */
    public Date getReadTime() 
    {
        return readTime;
    }
    /**
            *  设置 ReadNum
    * @param 阅读次数
    */
    public void setReadNum(Long readNum) 
    {
        this.readNum = readNum;
    }
    /**
             *  获取 阅读次数
     * @return
     */
    public Long getReadNum() 
    {
        return readNum;
    }
    /**
            *  设置 CreateUserId
    * @param 员工ID
    */
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }
    /**
             *  获取 员工ID
     * @return
     */
    public Long getCreateUserId() 
    {
        return createUserId;
    }
    /**
            *  设置 UpdateUserId
    * @param 更新员工
    */
    public void setUpdateUserId(Long updateUserId) 
    {
        this.updateUserId = updateUserId;
    }
    /**
             *  获取 更新员工
     * @return
     */
    public Long getUpdateUserId() 
    {
        return updateUserId;
    }
    /**
            *  设置 BulletinId
    * @param 公告ID
    */
    public void setBulletinId(String bulletinId) 
    {
        this.bulletinId = bulletinId;
    }
    /**
             *  获取 公告ID
     * @return
     */
    public String getBulletinId() 
    {
        return bulletinId;
    }
    /**
            *  设置 Sts
    * @param A:已阅读，B:已删除 C:未阅读
    */
    public void setSts(String sts) 
    {
        this.sts = sts;
    }
    /**
             *  获取 A:已阅读，B:已删除 C:未阅读
     * @return
     */
    public String getSts() 
    {
        return sts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reciveId", getReciveId())
            .append("reciveUserId", getReciveUserId())
            .append("readTime", getReadTime())
            .append("readNum", getReadNum())
            .append("createTime", getCreateTime())
            .append("createUserId", getCreateUserId())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("bulletinId", getBulletinId())
            .append("sts", getSts())
            .toString();
    }
}
