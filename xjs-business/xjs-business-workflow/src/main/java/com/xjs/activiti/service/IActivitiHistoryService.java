package com.xjs.activiti.service;


import com.xjs.activiti.domain.dto.ActivitiHighLineDTO;

/**
 * Activiti历史任务服务接口
 *
 * @author xiejs
 * @since 2022-04-17 01:48:55
 */
public interface IActivitiHistoryService {
    /**
     * 获取高亮
     * @param instanceId 流程实例id
     * @return ActivitiHighLineDTO
     */
    ActivitiHighLineDTO gethighLine(String instanceId);
}
