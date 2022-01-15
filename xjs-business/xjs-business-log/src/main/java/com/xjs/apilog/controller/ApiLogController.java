package com.xjs.apilog.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.apilog.domain.ApiLog;
import com.xjs.apilog.service.IApiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 日志Controller
 *
 * @author xjs
 * @date 2021-12-26
 */
@RestController
@RequestMapping("apilog")
@Api(tags = "业务模块-API日志")
public class ApiLogController extends BaseController {
    @Autowired
    private IApiLogService apiLogService;


    @PostMapping("forPRC")
    @ApiOperation("供AOP切面RPC远程调用")
    public R<Object> saveApiLog(@RequestBody ApiLog apiLog) {

        boolean save = apiLogService.save(apiLog);
        return save?R.ok():R.fail();
    }


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询日志列表
     */
    @RequiresPermissions("log:apilog:list")
    @GetMapping("/list")
    @ApiOperation("查询日志列表")
    public TableDataInfo list(ApiLog apiLog) {
        startPage();
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        return getDataTable(list);
    }

    /**
     * 导出日志列表
     */
    @RequiresPermissions("log:apilog:export")
    @Log(title = "API日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出日志列表")
    public void export(HttpServletResponse response, ApiLog apiLog) {
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        ExcelUtil<ApiLog> util = new ExcelUtil<ApiLog>(ApiLog.class);
        util.exportExcel(response, list, "日志数据");
    }

    /**
     * 获取日志详细信息
     */
    @RequiresPermissions("log:apilog:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取日志详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiLogService.selectApiLogById(id));
    }


    /**
     * 删除日志
     */
    @RequiresPermissions("log:apilog:remove")
    @Log(title = "API日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除日志")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiLogService.deleteApiLogByIds(ids));
    }
}
