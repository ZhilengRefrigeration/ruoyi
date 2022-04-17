package com.xjs.activiti.service;


import com.xjs.activiti.domain.dto.HistoryDataDTO;

import java.util.List;

/**
 * 历史数据表单服务接口
 *
 * @author xiejs
 * @since 2022-04-17 01:50:13
 */
public interface IFormHistoryDataService {

    /**
     * 查询历史数据展示
     * @param instanceId 流程实例id
     * @return list
     */
    List<HistoryDataDTO> historyDataShow(String instanceId);
}
