package com.xjs.workflow.leave.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 请假对象 workflow_leave
 *
 * @author xiejs
 * @since 2022-04-17 17:53:55
 */
public class WorkflowLeave extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 请假类型
     */
    @Excel(name = "请假类型")
    @NotBlank(message = "请假类型不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 1, max = 5, message = "请假类型长度不能超过 10 个字符", groups = {SelectGroup.class, AddGroup.class, UpdateGroup.class})
    private String type;

    /**
     * 标题
     */
    @Excel(name = "标题")
    @NotBlank(message = "请假标题不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 1, max = 100, message = "请假标题长度不能超过 10 个字符", groups = {SelectGroup.class, AddGroup.class, UpdateGroup.class})
    private String title;

    /**
     * 原因
     */
    @Excel(name = "原因")
    @NotBlank(message = "请假原因不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 1, max = 500, message = "请假原因长度不能超过 500 个字符", groups = {AddGroup.class, UpdateGroup.class})
    private String reason;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date leaveStartTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date leaveEndTime;


    private String instanceId;
    private String taskName;

    /**
     * 状态
     */
    @Excel(name = "状态", readConverterExp = "0=进行中,1=成功,2=失败")
    @Size(min = 1, max = 1, message = "请假状态长度不能超过 1 个字符", groups = {SelectGroup.class})
    private String state;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createName;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("title", getTitle())
                .append("reason", getReason())
                .append("leaveStartTime", getLeaveStartTime())
                .append("leaveEndTime", getLeaveEndTime())
                .append("instanceId", getInstanceId())
                .append("state", getState())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
