package com.xjs.activiti.domain.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import org.activiti.api.task.model.Task;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Date;

/**
 * act任务dto
 * @author xiejs
 * @since 2022-04-17 02:08:24
 */
public class ActTaskDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    private String processInstanceId;

    private String name;

    private String status;

    private Date createdDate;

    private Date endDate;

    private String instanceName;
    private String definitionKey;
    private String businessKey;

    /**
     * 上一个节点处理人
     */
    private String assignee;

    public ActTaskDTO() {
    }

    public ActTaskDTO(Task task, ProcessInstance processInstance) {
        this.id = task.getId();
        this.processInstanceId = task.getProcessInstanceId();
        this.name = task.getName();
        this.status = task.getStatus().toString();
        this.createdDate = task.getCreatedDate();
        this.instanceName = processInstance.getName();
        this.definitionKey = processInstance.getProcessDefinitionKey();
        this.businessKey = processInstance.getBusinessKey();
    }

    public ActTaskDTO(HistoricTaskInstance historicTaskInstance, HistoricProcessInstance processInstance) {
        this.id = historicTaskInstance.getId();
        this.processInstanceId = historicTaskInstance.getProcessInstanceId();
        this.name = historicTaskInstance.getName();
        this.createdDate = historicTaskInstance.getCreateTime();
        this.endDate = historicTaskInstance.getEndTime();
        this.instanceName = processInstance.getName();
        this.definitionKey = processInstance.getProcessDefinitionKey();
        this.businessKey = processInstance.getBusinessKey();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getDefinitionKey() {
        return definitionKey;
    }

    public void setDefinitionKey(String definitionKey) {
        this.definitionKey = definitionKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
