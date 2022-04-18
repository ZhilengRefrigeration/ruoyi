package com.xjs.activiti.domain.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.xjs.activiti.domain.vo.ActReDeploymentVO;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * act流程定义dto
 *
 * @author xiejs
 * @since 2022-04-17 02:10:06
 */
public class ProcessDefinitionDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 流程名称
     */
    @Size(min = 1, max = 50, message = "流程名称不能超过 50 个字符")
    private String name;

    /**
     * 流程key
     */
    @Size(min = 1, max = 20, message = "流程key不能超过 20 个字符")
    private String key;

    /**
     * 流程版本号
     */
    private int version;

    /**
     * 部署id
     */
    private String deploymentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 部署时间
     */
    private Date deploymentTime;


    /**
     * 流程实例状态 1 激活 2 挂起
     */
    private Integer suspendState;

    public ProcessDefinitionDTO() {
    }

    public ProcessDefinitionDTO(ProcessDefinitionEntityImpl processDefinition, ActReDeploymentVO actReDeploymentVO) {
        this.id = processDefinition.getId();
        this.name = processDefinition.getName();
        this.key = processDefinition.getKey();
        this.version = processDefinition.getVersion();
        this.deploymentId = processDefinition.getDeploymentId();
        this.resourceName = processDefinition.getResourceName();
        this.deploymentTime = actReDeploymentVO.getDeployTime();
        this.suspendState = processDefinition.getSuspensionState();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }


    public Integer getSuspendState() {
        return suspendState;
    }

    public void setSuspendState(Integer suspendState) {
        this.suspendState = suspendState;
    }
}
