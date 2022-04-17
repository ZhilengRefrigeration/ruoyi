package com.xjs.activiti.domain.dto;

import org.activiti.engine.repository.ProcessDefinition;

/**
 * act定义对象id dto
 * @author xiejs
 * @since 2022-04-17 02:09:10
 */
public class DefinitionIdDTO {
    private String deploymentID;
    private String resourceName;

    public String getDeploymentID() {
        return deploymentID;
    }

    public void setDeploymentID(String deploymentID) {
        this.deploymentID = deploymentID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public DefinitionIdDTO() {
    }

    public DefinitionIdDTO(ProcessDefinition processDefinition) {
        this.deploymentID = processDefinition.getDeploymentId();
        this.resourceName = processDefinition.getResourceName();
    }
}
