package com.xjs.log.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.log.domain.ApiLog;
import com.xjs.log.service.IApiLogService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 日志Controller
 *
 * @author xjs
 * @date 2021-12-26
 */
@RestController
@RequestMapping("log")
public class ApiLogController extends BaseController {
    @Autowired
    private IApiLogService apiLogService;

    /**
     * 查询日志列表
     */
    @RequiresPermissions("english:log:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiLog apiLog) {
        startPage();
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        return getDataTable(list);
    }

    /**
     * 导出日志列表
     */
    @RequiresPermissions("english:log:export")
    @Log(title = "API日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiLog apiLog) {
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        ExcelUtil<ApiLog> util = new ExcelUtil<ApiLog>(ApiLog.class);
        util.exportExcel(response, list, "日志数据");
    }

    /**
     * 获取日志详细信息
     */
    @RequiresPermissions("english:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiLogService.selectApiLogById(id));
    }


    /**
     * 删除日志
     */
    @RequiresPermissions("english:log:remove")
    @Log(title = "API日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiLogService.deleteApiLogByIds(ids));
    }
}
