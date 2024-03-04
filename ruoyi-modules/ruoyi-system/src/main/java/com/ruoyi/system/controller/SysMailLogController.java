package com.ruoyi.system.controller;

import com.ruoyi.common.core.mail.MailSendResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysMailLog;
import com.ruoyi.system.domain.vo.MailVo;
import com.ruoyi.system.service.ISysMailLogService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 邮件发送日志Controller
 *
 * @author ryas
 * created on 2024-03-01
 */
@RestController
@RequestMapping("/mailLog")
public class SysMailLogController extends BaseController {
    @Autowired
    private ISysMailLogService sysMailLogService;

    /**
     * 查询邮件发送日志列表
     */
    @RequiresPermissions("system:mailLog:list")
    @GetMapping("/list")
    public TableDataInfo list(SysMailLog sysMailLog) {
        startPage();
        List<SysMailLog> list = sysMailLogService.selectSysMailLogList(sysMailLog);
        return getDataTable(list);
    }

    /**
     * 导出邮件发送日志列表
     */
    @RequiresPermissions("system:mailLog:export")
    @Log(title = "邮件发送日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMailLog sysMailLog) {
        List<SysMailLog> list = sysMailLogService.selectSysMailLogList(sysMailLog);
        ExcelUtil<SysMailLog> util = new ExcelUtil<>(SysMailLog.class);
        util.exportExcel(response, list, "邮件发送日志数据");
    }

    /**
     * 获取邮件发送日志详细信息
     */
    @RequiresPermissions("system:mailLog:query")
    @GetMapping(value = "/{mailLogId}")
    public AjaxResult getInfo(@PathVariable("mailLogId") Long mailLogId) {
        return success(sysMailLogService.selectSysMailLogByMailLogId(mailLogId));
    }

    /**
     * 删除邮件发送日志
     */
    @RequiresPermissions("system:mailLog:remove")
    @Log(title = "邮件发送日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{mailLogIds}")
    public AjaxResult remove(@PathVariable Long[] mailLogIds) {
        return toAjax(sysMailLogService.deleteSysMailLogByMailLogIds(mailLogIds));
    }

    /**
     * 临时邮件发送
     */
    @RequiresPermissions("system:mailLog:send")
    @Log(title = "临时邮件发送", businessType = BusinessType.OTHER)
    @PostMapping("/sendTemporality")
    public AjaxResult sendTemporality(MailVo mailVo) {
        MailSendResult result = sysMailLogService.sendSimpleMail(mailVo);
        if (result.isSuccess()) {
            return success();
        } else {
            return error(result.getErrMsg());
        }
    }

    /**
     * 获取邮件发送者信息
     */
    @RequiresPermissions("system:mailLog:query")
    @GetMapping(value = "/getMailSenderInfo")
    public AjaxResult getMailSenderInfo() {
        return success(sysMailLogService.getMailSenderInfo());
    }
}
