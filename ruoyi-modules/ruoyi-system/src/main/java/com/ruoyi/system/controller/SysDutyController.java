package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.api.domain.SysDept;
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
import com.ruoyi.system.domain.SysDuty;
import com.ruoyi.system.service.ISysDutyService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 职称Controller
 * 
 * @author ruoyi
 * @date 2023-10-29
 */
@RestController
@RequestMapping("/duty")
public class SysDutyController extends BaseController
{
    @Autowired
    private ISysDutyService sysDutyService;

    /**
     * 查询职称列表
     */
    @RequiresPermissions("system:duty:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDuty sysDuty)
    {
        startPage();
        List<SysDuty> list = sysDutyService.selectSysDutyList(sysDuty);
        return getDataTable(list);
    }

    /**
     * 导出职称列表
     */
    @RequiresPermissions("system:duty:export")
    @Log(title = "职称", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDuty sysDuty)
    {
        List<SysDuty> list = sysDutyService.selectSysDutyList(sysDuty);
        ExcelUtil<SysDuty> util = new ExcelUtil<SysDuty>(SysDuty.class);
        util.exportExcel(response, list, "职称数据");
    }

    /**
     * 获取职称详细信息
     */
    @RequiresPermissions("system:duty:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysDutyService.selectSysDutyById(id));
    }

    /**
     * 新增职称
     */
    @RequiresPermissions("system:duty:add")
    @Log(title = "职称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDuty sysDuty)
    {
        return toAjax(sysDutyService.insertSysDuty(sysDuty));
    }

    /**
     * 修改职称
     */
    @RequiresPermissions("system:duty:edit")
    @Log(title = "职称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDuty sysDuty)
    {
        return toAjax(sysDutyService.updateSysDuty(sysDuty));
    }

    /**
     * 删除职称
     */
    @RequiresPermissions("system:duty:remove")
    @Log(title = "职称", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDutyService.deleteSysDutyByIds(ids));
    }

    /**
     * 获取对应角色部门树列表
     */
    @RequiresPermissions("system:duty:list")
    @GetMapping(value = "/dutyTree")
    public AjaxResult deptTree() {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("dutys", sysDutyService.selectDutyTreeList());
        return ajax;
    }
}
