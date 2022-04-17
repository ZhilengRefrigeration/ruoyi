package com.xjs.activiti.service;

import com.github.pagehelper.Page;
import com.ruoyi.common.core.web.page.PageDomain;
import com.xjs.activiti.domain.dto.ActTaskDTO;
import com.xjs.activiti.domain.dto.ActWorkflowFormDataDTO;

import java.text.ParseException;
import java.util.List;

/**
 * Activiti任务服务接口
 *
 * @author xiejs
 * @since 2022-04-17 01:54:51
 */
public interface IActTaskService {

    /**
     * 查询流程定义列表
     * @param pageDomain 分页参数
     * @return
     */
    Page<ActTaskDTO> selectProcessDefinitionList(PageDomain pageDomain);

    /**
     * 根据任务id查询表格数据
     *
     * @param taskID 任务id
     * @return list
     */
    List<String> formDataShow(String taskID);


    /**
     * 表格数据保存
     * @param taskID 任务id
     * @param awfs
     * @return
     * @throws ParseException
     */
    int formDataSave(String taskID, List<ActWorkflowFormDataDTO> awfs) throws ParseException;
}
