package com.ruoyi.potenza.controller;

import java.util.List;
import java.io.IOException;
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
import com.ruoyi.potenza.domain.TbBorrowerPeriods;
import com.ruoyi.potenza.service.TbBorrowerPeriodsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @author 木子
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 16:00
 */
@RequestMapping("periods")
@RestController
@Slf4j
public class BorrowerPeriodsController extends BaseController{
    @Autowired
    private TbBorrowerPeriodsService tbBorrowerPeriodsService;

    /**
     * 查询贷款周期列表
     */
    @RequiresPermissions("potenza:periods:list")
    @GetMapping("/list")
    public TableDataInfo list(TbBorrowerPeriods tbBorrowerPeriods)
    {
        startPage();
        List<TbBorrowerPeriods> list = tbBorrowerPeriodsService.selectTbBorrowerPeriodsList(tbBorrowerPeriods);
        return getDataTable(list);
    }

    /**
     * 导出贷款周期列表
     */
    @Log(title = "贷款周期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbBorrowerPeriods tbBorrowerPeriods)
    {
        List<TbBorrowerPeriods> list = tbBorrowerPeriodsService.selectTbBorrowerPeriodsList(tbBorrowerPeriods);
        ExcelUtil<TbBorrowerPeriods> util = new ExcelUtil<TbBorrowerPeriods>(TbBorrowerPeriods.class);
        util.exportExcel(response, list, "贷款周期数据");
    }

    /**
     * 获取贷款周期详细信息
     */
    @RequiresPermissions("potenza:periods:query")
    @GetMapping(value = "/{periodsById}")
    public AjaxResult getInfo(@PathVariable("periodsId") Long periodsId)
    {
        return success(tbBorrowerPeriodsService.selectTbBorrowerPeriodsByPeriodsId(periodsId));
    }

    /**
     * 新增贷款周期
     */
    @RequiresPermissions("potenza:periods:add")
    @Log(title = "贷款周期", businessType = BusinessType.INSERT)
    @PostMapping("periodsInsert")
    public AjaxResult add(@RequestBody TbBorrowerPeriods tbBorrowerPeriods)
    {
        return toAjax(tbBorrowerPeriodsService.insertTbBorrowerPeriods(tbBorrowerPeriods));
    }

    /**
     * 修改贷款周期
     */
    @RequiresPermissions("potenza:periods:edit")
    @Log(title = "贷款周期", businessType = BusinessType.UPDATE)
    @PutMapping("/periodsUpdate")
    public AjaxResult edit(@RequestBody TbBorrowerPeriods tbBorrowerPeriods)
    {
        return toAjax(tbBorrowerPeriodsService.updateTbBorrowerPeriods(tbBorrowerPeriods));
    }

    /**
     * 删除贷款周期
     */
    @RequiresPermissions("potenza:periods:remove")
    @Log(title = "贷款周期", businessType = BusinessType.DELETE)
    @DeleteMapping("/{periodsIds}")
    public AjaxResult remove(@PathVariable Long[] periodsIds)
    {
        return toAjax(tbBorrowerPeriodsService.deleteTbBorrowerPeriodsByPeriodsIds(periodsIds));
    }
}
