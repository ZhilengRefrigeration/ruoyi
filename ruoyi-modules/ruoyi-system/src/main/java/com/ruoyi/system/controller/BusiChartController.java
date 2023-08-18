package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.BusiChart;
import com.ruoyi.system.service.IBusiChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试图Controller
 * 
 * @author ruoyi
 * @date 2023-07-21
 */
@RestController
@RequestMapping("/chart")
public class BusiChartController extends BaseController
{
    @Autowired
    private IBusiChartService busiChartService;

    /**
     * 查询测试图列表
     */
    @RequiresPermissions("system:chart:list")
    @GetMapping("/list")
    public TableDataInfo list(BusiChart busiChart)
    {
        startPage();
        List<BusiChart> list = busiChartService.selectBusiChartList(busiChart);
        return getDataTable(list);
    }

    /**
     * 导出测试图列表
     */
    @RequiresPermissions("system:chart:export")
    @Log(title = "测试图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusiChart busiChart)
    {
        List<BusiChart> list = busiChartService.selectBusiChartList(busiChart);
        ExcelUtil<BusiChart> util = new ExcelUtil<BusiChart>(BusiChart.class);
        util.exportExcel(response, list, "测试图数据");
    }

    /**
     * 获取测试图详细信息
     */
    @RequiresPermissions("system:chart:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(busiChartService.selectBusiChartById(id));
    }

    /**
     * 新增测试图
     */
    @RequiresPermissions("system:chart:add")
    @Log(title = "测试图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusiChart busiChart)
    {
        return toAjax(busiChartService.insertBusiChart(busiChart));
    }

    /**
     * 修改测试图
     */
    @RequiresPermissions("system:chart:edit")
    @Log(title = "测试图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusiChart busiChart)
    {
        return toAjax(busiChartService.updateBusiChart(busiChart));
    }

    /**
     * 删除测试图
     */
    @RequiresPermissions("system:chart:remove")
    @Log(title = "测试图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busiChartService.deleteBusiChartByIds(ids));
    }
}
