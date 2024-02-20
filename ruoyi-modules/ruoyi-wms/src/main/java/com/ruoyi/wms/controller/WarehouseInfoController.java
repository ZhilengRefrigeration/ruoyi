package com.ruoyi.wms.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.WarehouseInfo;
import com.ruoyi.wms.service.IWarehouseInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库基础信息Controller
 *
 * @author ryas
 * created on 2024-02-18
 */
@RestController
@RequestMapping("/WarehouseInfo")
public class WarehouseInfoController extends BaseController {
    @Autowired
    private IWarehouseInfoService warehouseInfoService;

    /**
     * 查询仓库基础信息列表
     */
    @RequiresPermissions("wms:WarehouseInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(WarehouseInfo warehouseInfo) {
        startPage();
        List<WarehouseInfo> list = warehouseInfoService.selectWarehouseInfoList(warehouseInfo);
        return getDataTable(list);
    }

    /**
     * 导出仓库基础信息列表
     */
    @RequiresPermissions("wms:WarehouseInfo:export")
    @Log(title = "仓库基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WarehouseInfo warehouseInfo) {
        List<WarehouseInfo> list = warehouseInfoService.selectWarehouseInfoList(warehouseInfo);
        if (list.isEmpty()) {
            responseJsonWarn(response, "没有数据可以导出");
            return;
        }
        ExcelUtil<WarehouseInfo> util = new ExcelUtil<>(WarehouseInfo.class);
        util.exportExcel(response, list, "仓库基础信息数据");
    }

    /**
     * 获取仓库基础信息详细信息
     */
    @RequiresPermissions("wms:WarehouseInfo:query")
    @GetMapping(value = "/{whsCd}")
    public AjaxResult getInfo(@PathVariable("whsCd") String whsCd) {
        return success(warehouseInfoService.selectWarehouseInfoByWhsCd(whsCd));
    }

    /**
     * 新增仓库基础信息
     */
    @RequiresPermissions("wms:WarehouseInfo:add")
    @Log(title = "仓库基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WarehouseInfo warehouseInfo) {
        return toAjax(warehouseInfoService.insertWarehouseInfo(warehouseInfo));
    }

    /**
     * 修改仓库基础信息
     */
    @RequiresPermissions("wms:WarehouseInfo:edit")
    @Log(title = "仓库基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WarehouseInfo warehouseInfo) {
        return toAjax(warehouseInfoService.updateWarehouseInfo(warehouseInfo));
    }

    /**
     * 删除仓库基础信息
     */
    @RequiresPermissions("wms:WarehouseInfo:remove")
    @Log(title = "仓库基础信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{whsCds}")
    public AjaxResult remove(@PathVariable String[] whsCds) {
        return toAjax(warehouseInfoService.deleteWarehouseInfoByWhsCds(whsCds));
    }
}
