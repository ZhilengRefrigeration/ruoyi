package com.xjs.reptileLog.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.reptileLog.domain.WebmagicLog;
import com.xjs.reptileLog.service.WebmagicLogService;
import com.xjs.validation.group.SelectGroup;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 爬虫日志控制器
 *
 * @author xiejs
 * @since 2022-02-17
 */
@RestController
@RequestMapping("reptileLog")
@Api(tags = "业务模块-爬虫日志")
public class WebmagicLogController extends MyBaseController {

    @Autowired
    private WebmagicLogService webmagicLogService;


    //-----------------------内部调用rpc------------------------

    @PostMapping("saveForPRC")
    @ApiOperation("供AOP切面RPC远程调用")
    public R<Object> saveReptileLog(@RequestBody WebmagicLog webmagicLog) {
        boolean save = webmagicLogService.save(webmagicLog);
        return save ? R.ok() : R.fail();
    }


    //------------------代码生成----------------------------

    /**
     * 查询爬虫日志列表
     */
    @RequiresPermissions("log:webmagicLog:list")
    @GetMapping("/list")
    @ApiOperation("查询爬虫日志列表")
    public TableDataInfo list(@Validated({SelectGroup.class}) WebmagicLog webmagicLog) {
        startPage();
        List<WebmagicLog> list = webmagicLogService.selectWebmagicLogList(webmagicLog);
        return getDataTable(list);
    }

    /**
     * 导出爬虫日志列表
     */
    @RequiresPermissions("log:webmagicLog:export")
    @Log(title = "爬虫日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出爬虫日志列表")
    public void export(HttpServletResponse response, WebmagicLog webmagicLog) {
        List<WebmagicLog> list = webmagicLogService.selectWebmagicLogList(webmagicLog);
        ExcelUtil<WebmagicLog> util = new ExcelUtil<>(WebmagicLog.class);
        util.exportExcel(response, list, "爬虫日志数据");
    }

    /**
     * 删除爬虫日志
     */
    @RequiresPermissions("log:webmagicLog:remove")
    @Log(title = "爬虫日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除爬虫日志")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(webmagicLogService.deleteWebmagicLogByIds(ids));
    }


}
