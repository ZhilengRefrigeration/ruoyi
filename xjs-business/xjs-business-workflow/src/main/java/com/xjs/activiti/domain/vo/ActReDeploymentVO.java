package com.xjs.activiti.domain.vo;

import java.util.Date;


/**
 * Act部署vo
 * @author xiejs
 * @since 2022-04-17 02:07:39
 */
public class ActReDeploymentVO {
    private String id;
    private Date deployTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}
