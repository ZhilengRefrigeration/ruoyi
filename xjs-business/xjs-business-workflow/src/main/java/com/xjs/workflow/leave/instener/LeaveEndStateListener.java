package com.xjs.workflow.leave.instener;

import com.ruoyi.common.core.utils.SpringUtils;
import com.xjs.workflow.leave.domain.WorkflowLeave;
import com.xjs.workflow.leave.service.IWorkflowLeaveService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

/**
 * act监听器
 * @author xiejs
 * @since 2022-04-17 17:54:27
 */
public class LeaveEndStateListener implements ExecutionListener {
    private Expression state;

    @Override
    public void notify(DelegateExecution delegateExecution) {
        WorkflowLeave workflowLeave = new WorkflowLeave();
        workflowLeave.setId(delegateExecution.getProcessInstanceBusinessKey());
        workflowLeave.setState(state.getValue(delegateExecution).toString());
        SpringUtils.getBean(IWorkflowLeaveService.class).updateWorkflowLeave(workflowLeave);
    }
}
