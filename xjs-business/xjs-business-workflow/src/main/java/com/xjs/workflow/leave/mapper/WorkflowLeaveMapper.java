package com.xjs.workflow.leave.mapper;

import com.xjs.workflow.leave.domain.WorkflowLeave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请假Mapper接口
 *
 * @author xiejs
 * @since 2022-04-17 17:54:39
 */
public interface WorkflowLeaveMapper {
    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    WorkflowLeave selectWorkflowLeaveById(String id);

    /**
     * 查询请假
     *
     * @param instanceId 请假ID
     * @return 请假
     */
    WorkflowLeave selectWorkflowLeaveByInstanceId(String instanceId);

    /**
     * 查询请假列表根据部门编号和WorkflowLeave
     *
     * @param workflowLeave 请假
     * @return 请假集合
     */
    List<WorkflowLeave> selectWorkflowLeaveListByWorkflowLeaveAndDeptId(@Param("workflowLeave") WorkflowLeave workflowLeave, @Param("deptId") Long deptId);

    /**
     * 查询请假列表
     *
     * @param workflowLeave 请假
     * @return 请假集合
     */
    List<WorkflowLeave> selectWorkflowLeaveList(WorkflowLeave workflowLeave);


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
     * 删除请假
     *
     * @param id 请假ID
     * @return 结果
     */
    int deleteWorkflowLeaveById(String id);

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWorkflowLeaveByIds(String[] ids);
}
