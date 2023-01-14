package com.ruoyi.potenza.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
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
import com.ruoyi.potenza.domain.TbBorrowerPlan;
import com.ruoyi.potenza.service.TbBorrowerPlanService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @author 木子
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 16:01
 */
@RequestMapping("plan")
@RestController
@Slf4j
public class BorrowerPlanController extends BaseController{

    @Autowired
    private TbBorrowerPlanService tbBorrowerPlanService;

    /**
     * 查询计划列表
     */
    @RequiresPermissions("potenza:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(TbBorrowerPlan tbBorrowerPlan)
    {
        startPage();
        List<TbBorrowerPlan> list = tbBorrowerPlanService.selectTbBorrowerPlanList(tbBorrowerPlan);
        return getDataTable(list);
    }

    /**
     * 导出计划列表
     */
    @RequiresPermissions("potenza:plan:export")
    @Log(title = "计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbBorrowerPlan tbBorrowerPlan)
    {
        List<TbBorrowerPlan> list = tbBorrowerPlanService.selectTbBorrowerPlanList(tbBorrowerPlan);
        ExcelUtil<TbBorrowerPlan> util = new ExcelUtil<TbBorrowerPlan>(TbBorrowerPlan.class);
        util.exportExcel(response, list, "计划数据");
    }

    /**
     * 获取计划详细信息
     */
    @RequiresPermissions("potenza:plan:query")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return success(tbBorrowerPlanService.selectTbBorrowerPlanByPlanId(planId));
    }

    /**
     * 新增计划
     */
    @RequiresPermissions("potenza:plan:add")
    @Log(title = "计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbBorrowerPlan tbBorrowerPlan)
    {
        return toAjax(tbBorrowerPlanService.insertTbBorrowerPlan(tbBorrowerPlan));
    }

    /**
     * 修改计划
     */
    @RequiresPermissions("potenza:plan:edit")
    @Log(title = "计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbBorrowerPlan tbBorrowerPlan)
    {
        return toAjax(tbBorrowerPlanService.updateTbBorrowerPlan(tbBorrowerPlan));
    }

    /**
     * 删除计划
     */
    @RequiresPermissions("potenza:plan:remove")
    @Log(title = "计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(tbBorrowerPlanService.deleteTbBorrowerPlanByPlanIds(planIds));
    }
}
