package com.xjs.workflow.leave.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.text.UUID;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.xjs.workflow.leave.domain.WorkflowLeave;
import com.xjs.workflow.leave.mapper.WorkflowLeaveMapper;
import com.xjs.workflow.leave.service.IWorkflowLeaveService;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xjs.consts.WorkflowConst.WorkflowEnum.ING;

/**
 * 请假Service业务层处理
 *
 * @author xiejs
 * @since 2022-04-17 17:55:10
 */
@Service
public class WorkflowLeaveServiceImpl implements IWorkflowLeaveService {

    @Resource
    private WorkflowLeaveMapper workflowLeaveMapper;
    //7.0的需要整合springSercuity
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private RuntimeService runtimeService;
    @Resource
    private RemoteUserService remoteUserService;
    @Autowired
    private TaskService taskService;


    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    @Override
    public WorkflowLeave selectWorkflowLeaveById(String id) {
        return workflowLeaveMapper.selectWorkflowLeaveById(id);
    }

    /**
     * 查询请假列表
     *
     * @param workflowLeave 请假
     * @return 请假
     */
    @Override
    public List<WorkflowLeave> selectWorkflowLeaveList(WorkflowLeave workflowLeave) {
        return workflowLeaveMapper.selectWorkflowLeaveListByWorkflowLeaveAndDeptId(workflowLeave, SecurityUtils.getLoginUser().getSysUser().getDeptId());
    }

    /**
     * 查询请假列表带任务状态
     *
     * @param workflowLeave 请假
     * @return 请假
     */
    @Override
    public List<WorkflowLeave> selectWorkflowLeaveAndTaskNameList(WorkflowLeave workflowLeave) {
        List<WorkflowLeave> workflowLeaves = workflowLeaveMapper.selectWorkflowLeaveList(workflowLeave);
        List<String> collect = workflowLeaves.parallelStream().map(WorkflowLeave::getInstanceId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(collect)) {
            List<Task> tasks = taskService.createTaskQuery().processInstanceIdIn(collect).list();
            workflowLeaves.forEach(
                    wl -> {
                        Task task = tasks.parallelStream().filter(t -> t.getProcessInstanceId().equals(wl.getInstanceId())).findAny().orElse(null);
                        if (task != null) {
                            wl.setTaskName(task.getName());
                        }
                    }
            );
        }
        return workflowLeaves;
    }

    /**
     * 新增请假
     *
     * @param workflowLeave 请假
     * @return 结果
     */
    @Override
    public int insertWorkflowLeave(WorkflowLeave workflowLeave) {
        String id = UUID.randomUUID().toString();
        workflowLeave.setId(id);
        workflowLeave.setCreateTime(DateUtils.getNowDate());
        String join = StringUtils.join(remoteUserService.selectUserNameByPostCodeAndDeptId("se", SecurityUtils.getLoginUser().getSysUser().getDeptId()), ",");

        Map<String, Object> map = new HashMap<>();
        map.put("deptLeader", join);
        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .variables(map)
                .processDefinitionKey("leave")
                .businessKey(id)
                .name(workflowLeave.getTitle())
                .start();

        workflowLeave.setInstanceId(processInstance.getId());
        workflowLeave.setState(String.valueOf(ING.getCode()));
        workflowLeave.setCreateName(SecurityUtils.getLoginUser().getSysUser().getNickName());
        workflowLeave.setCreateBy(SecurityUtils.getUsername());
        workflowLeave.setCreateTime(DateUtils.getNowDate());
        return workflowLeaveMapper.insertWorkflowLeave(workflowLeave);
    }

    /**
     * 修改请假
     *
     * @param workflowLeave 请假
     * @return 结果
     */
    @Override
    public int updateWorkflowLeave(WorkflowLeave workflowLeave) {
        workflowLeave.setUpdateTime(DateUtils.getNowDate());
        return workflowLeaveMapper.updateWorkflowLeave(workflowLeave);
    }

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的请假ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowLeaveByIds(String[] ids) {
        return workflowLeaveMapper.deleteWorkflowLeaveByIds(ids);
    }

    /**
     * 删除请假信息
     *
     * @param id 请假ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowLeaveById(String id) {
        return workflowLeaveMapper.deleteWorkflowLeaveById(id);
    }

    @Override
    public WorkflowLeave selectWorkflowLeaveByInstanceId(String instanceId) {

        return workflowLeaveMapper.selectWorkflowLeaveByInstanceId(instanceId);
    }
}
