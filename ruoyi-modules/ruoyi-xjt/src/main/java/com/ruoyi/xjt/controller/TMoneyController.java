package com.ruoyi.xjt.controller;

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
import com.ruoyi.xjt.domain.TMoney;
import com.ruoyi.xjt.service.ITMoneyService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 支出或收入详情Controller
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@RestController
@RequestMapping("/money")
public class TMoneyController extends BaseController
{
    @Autowired
    private ITMoneyService tMoneyService;

    /**
     * 查询支出或收入详情列表
     */
    @RequiresPermissions("xjt:money:list")
    @GetMapping("/list")
    public TableDataInfo list(TMoney tMoney)
    {
        startPage();
        List<TMoney> list = tMoneyService.selectTMoneyList(tMoney);
        return getDataTable(list);
    }

    /**
     * 导出支出或收入详情列表
     */
    @RequiresPermissions("xjt:money:export")
    @Log(title = "支出或收入详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMoney tMoney)
    {
        List<TMoney> list = tMoneyService.selectTMoneyList(tMoney);
        ExcelUtil<TMoney> util = new ExcelUtil<TMoney>(TMoney.class);
        util.exportExcel(response, list, "支出或收入详情数据");
    }

    /**
     * 获取支出或收入详情详细信息
     */
    @RequiresPermissions("xjt:money:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tMoneyService.selectTMoneyById(id));
    }

    /**
     * 新增支出或收入详情
     */
    @RequiresPermissions("xjt:money:add")
    @Log(title = "支出或收入详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMoney tMoney)
    {
        return toAjax(tMoneyService.insertTMoney(tMoney));
    }

    /**
     * 修改支出或收入详情
     */
    @RequiresPermissions("xjt:money:edit")
    @Log(title = "支出或收入详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMoney tMoney)
    {
        return toAjax(tMoneyService.updateTMoney(tMoney));
    }

    /**
     * 删除支出或收入详情
     */
    @RequiresPermissions("xjt:money:remove")
    @Log(title = "支出或收入详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tMoneyService.deleteTMoneyByIds(ids));
    }
}
