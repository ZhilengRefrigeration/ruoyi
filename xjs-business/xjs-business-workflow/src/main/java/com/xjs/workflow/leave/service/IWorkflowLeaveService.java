package com.xjs.workflow.leave.service;


import com.xjs.workflow.leave.domain.WorkflowLeave;

import java.util.List;

/**
 * 请假Service接口
 *
 * @author xiejs
 * @since 2022-04-17 17:54:57
 */
public interface IWorkflowLeaveService {
    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    WorkflowLeave selectWorkflowLeaveById(String id);

    /**
     * 查询请假列表
     *
     * @param workflowLeave 请假
     * @return 请假集合
     */
    List<WorkflowLeave> selectWorkflowLeaveList(WorkflowLeave workflowLeave);

    /**
     * 查询请假列表
     *
     * @param workflowLeave 请假
     * @return 请假集合
     */
    List<WorkflowLeave> selectWorkflowLeaveAndTaskNameList(WorkflowLeave workflowLeave);

    /**
     * 新增请假
     *
     * @param workflowLeave 请假
     * @return 结果
     */
    int insertWorkflowLeave(WorkflowLeave workflowLeave);

    /**
     * 修改请假
     *
     * @param workflowLeave 请假
     * @return 结果
     */
    int updateWorkflowLeave(WorkflowLeave workflowLeave);

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的请假ID
     * @return 结果
     */
    int deleteWorkflowLeaveByIds(String[] ids);

    /**
     * 删除请假信息
     *
     * @param id 请假ID
     * @return 结果
     */
    int deleteWorkflowLeaveById(String id);


    WorkflowLeave selectWorkflowLeaveByInstanceId(String instanceId);
}
