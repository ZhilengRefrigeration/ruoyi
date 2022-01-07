package com.xjs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.domain.ApiRecord;
import com.xjs.domain.ApiWarning;
import com.xjs.service.ApiWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author xiejs
 * @desc api预警控制器
 * @create 2021-12-31
 */
@RestController
@RequestMapping("apiwarning")
public class ApiWarningController extends BaseController {

    @Autowired
    private ApiWarningService apiWarningService;

    /**
     * 远程保存 apiRecord
     *
     * @param apiRecord api记录
     * @return apiRecord
     */
    @PostMapping
    public R<ApiRecord> saveApiRecordForRPC(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.saveApiRecord(apiRecord) ? R.ok() : R.fail();
    }

    /**
     * 远程修改
     *
     * @param apiRecord api记录
     * @return ApiRecord
     */
    @PutMapping
    public R<ApiRecord> updateApiRecordForRPC(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.updateApiRecordByUrl(apiRecord) ? R.ok() : R.fail();
    }

    /**
     * 远程查询api记录信息
     *
     * @param apiRecord
     * @return R<List < ApiRecord>>
     */
    @GetMapping
    public R<List<ApiRecord>> selectApiRecordListForRPC(ApiRecord apiRecord) {
        List<ApiRecord> apiRecords = apiWarningService.selectApiRecordListByUrl(apiRecord);
        return R.ok(apiRecords);
    }

    /**
     * 远程保存api预警信息
     *
     * @param apiWarning 预警实体类
     * @return R
     */
    @PostMapping("saveApiwarningForRPC")
    public R<ApiWarning> saveApiWarningForRPC(@RequestBody ApiWarning apiWarning) {
        boolean save = apiWarningService.save(apiWarning);
        return save ? R.ok() : R.fail();
    }


    /**
     * 查询api预警列表
     */
    @RequiresPermissions("warning:warning:list")
    @GetMapping("/apiwarnlist")
    public TableDataInfo list(ApiWarning apiWarning) {
        startPage();
        List<ApiWarning> list = apiWarningService.list(new QueryWrapper<ApiWarning>()
                .like(Objects.nonNull(apiWarning.getApiName()),"api_name", apiWarning.getApiName()));
        return getDataTable(list);
    }

    /**
     * 导出api预警列表
     */
    @RequiresPermissions("warning:warning:export")
    @Log(title = "api预警", businessType = BusinessType.EXPORT)
    @PostMapping("/apiwarnexport")
    public void export(HttpServletResponse response, ApiWarning apiWarning) {
        List<ApiWarning> list = apiWarningService.list(new QueryWrapper<ApiWarning>()
                .like(Objects.nonNull(apiWarning.getApiName()),"api_name", apiWarning.getApiName()));
        ExcelUtil<ApiWarning> util = new ExcelUtil<ApiWarning>(ApiWarning.class);
        util.exportExcel(response, list, "api预警数据");
    }


    //-------------------------代码生成------------------------------------


    /**
     * 查询API预警信息列表
     */
    @RequiresPermissions("warning:apiwarning:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiRecord apiRecord) {
        startPage();
        List<ApiRecord> list = apiWarningService.selectApiRecordList(apiRecord);
        return getDataTable(list);
    }

    /**
     * 导出API预警信息列表
     */
    @RequiresPermissions("warning:apiwarning:export")
    @Log(title = "API预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiRecord apiRecord) {
        List<ApiRecord> list = apiWarningService.selectApiRecordList(apiRecord);
        ExcelUtil<ApiRecord> util = new ExcelUtil<ApiRecord>(ApiRecord.class);
        util.exportExcel(response, list, "API预警数据");
    }

    /**
     * 获取API预警详细信息信息
     */
    @RequiresPermissions("warning:apiwarning:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiWarningService.selectApiRecordById(id));
    }

    /**
     * 修改API预警信息
     */
    @RequiresPermissions("warning:apiwarning:edit")
    @Log(title = "API预警", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    public AjaxResult edit(@RequestBody ApiRecord apiRecord) {
        return toAjax(apiWarningService.updateApiRecord(apiRecord));
    }

}
