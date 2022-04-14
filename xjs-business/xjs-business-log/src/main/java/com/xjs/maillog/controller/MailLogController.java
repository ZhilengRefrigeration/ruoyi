package com.xjs.maillog.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.maillog.domain.MailLog;
import com.xjs.maillog.service.MailLogService;
import com.xjs.validation.group.SelectGroup;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 邮件日志Controller
 *
 * @author xiejs
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/maillog")
@Api(tags = "业务模块-邮件日志")
@Transactional
public class MailLogController extends MyBaseController<MailLog> {

    @Autowired
    private MailLogService mailLogService;

    //---------------------------远程调用-----------------------------------

    @PostMapping("saveForRPC")
    @ApiOperation("保存邮件日志ForRPC")
    public R<Object> saveMailLog(@RequestBody MailLog mailLog) {
        boolean save = mailLogService.save(mailLog);
        return save ? R.ok() : R.fail();
    }


    //---------------------------代码生成-----------------------------------

    /**
     * 查询邮件日志列表
     */
    @RequiresPermissions("log:maillog:list")
    @GetMapping("/list")
    @ApiOperation("查询邮件日志列表")
    public TableDataInfo list(@Validated({SelectGroup.class}) MailLog mailLog) {
        startPage();
        List<MailLog> list = mailLogService.selectMailLogList(mailLog);
        return getDataTable(list);
    }

    /**
     * 导出邮件日志列表
     */
    @RequiresPermissions("log:maillog:export")
    @Log(title = "邮件日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出邮件日志列表")
    public void export(HttpServletResponse response, MailLog mailLog) {
        List<MailLog> list = mailLogService.selectMailLogList(mailLog);
        ExcelUtil<MailLog> util = new ExcelUtil<MailLog>(MailLog.class);
        util.exportExcel(response, list, "邮件日志数据");
    }

    /**
     * 获取邮件日志详细信息
     */
    @RequiresPermissions("log:maillog:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取邮件日志详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(mailLogService.selectMailLogById(id));
    }


    /**
     * 删除邮件日志
     */
    @RequiresPermissions("log:maillog:remove")
    @Log(title = "邮件日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除邮件日志")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(mailLogService.deleteMailLogByIds(ids));
    }
}
