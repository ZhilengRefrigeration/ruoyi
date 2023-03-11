package com.ruoyi.system.domain;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import java.util.Collections;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 公告栏对象 t_bulletin_info
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public class BulletinInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private String bulletinId;

    /** 公告标题 */
    @Excel(name = "公告标题")
    @NotEmpty(message = "公告标题不允许为空")
    private String title;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String content;

    /** 已读人数 */
    @Excel(name = "已读人数")
    private Long readNum;

    /** 公告的发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "公告的发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 员工ID */
    @Excel(name = "员工ID")
    private Long createUserId;

    /** 更新员工 */
    @Excel(name = "更新员工")
    private Long updateUserId;

    /** 状态 */
    @Excel(name = "状态")
    @NotEmpty(message = "状态不允许为空")
    private String sts;

    /** 公告接收者信息 */
    private List<BulletinRecive> bulletinReciveList;
    /**
              *     给前端过渡，用于生成BulletinRecive
     */
    private List<Long> receiveStaffIds;
    /**
     * 接收人员，以逗号分隔
     */
    private String reciveStaffNames;
    
    
    public String getReciveStaffNames() {
		return reciveStaffNames;
	}
	public void setReciveStaffNames(String reciveStaffNames) {
		this.reciveStaffNames = reciveStaffNames;
	}
	public List<Long> getReceiveStaffIds() {
    	if(receiveStaffIds==null) {
    		receiveStaffIds=Collections.emptyList();
    	}
		return receiveStaffIds;
	}
	public void setReceiveStaffIds(List<Long> receiveStaffIds) {
		this.receiveStaffIds = receiveStaffIds;
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
            *  设置 Title
    * @param 公告标题
    */
    public void setTitle(String title) 
    {
        this.title = title;
    }
    /**
             *  获取 公告标题
     * @return
     */
    public String getTitle() 
    {
        return title;
    }
    /**
            *  设置 Content
    * @param 公告内容
    */
    public void setContent(String content) 
    {
        this.content = content;
    }
    /**
             *  获取 公告内容
     * @return
     */
    public String getContent() 
    {
        return content;
    }
    /**
            *  设置 ReadNum
    * @param 已读人数
    */
    public void setReadNum(Long readNum) 
    {
        this.readNum = readNum;
    }
    /**
             *  获取 已读人数
     * @return
     */
    public Long getReadNum() 
    {
        return readNum;
    }
    /**
            *  设置 SendTime
    * @param 公告的发送时间
    */
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }
    /**
             *  获取 公告的发送时间
     * @return
     */
    public Date getSendTime() 
    {
        return sendTime;
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
            *  设置 Sts
    * @param 状态
    */
    public void setSts(String sts) 
    {
        this.sts = sts;
    }
    /**
             *  获取 状态
     * @return
     */
    public String getSts() 
    {
        return sts;
    }

    public List<BulletinRecive> getBulletinReciveList()
    {
        return bulletinReciveList;
    }

    public void setBulletinReciveList(List<BulletinRecive> bulletinReciveList)
    {
        this.bulletinReciveList = bulletinReciveList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bulletinId", getBulletinId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("readNum", getReadNum())
            .append("sendTime", getSendTime())
            .append("createUserId", getCreateUserId())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("sts", getSts())
            .append("bulletinReciveList", getBulletinReciveList())
            .toString();
    }
}
