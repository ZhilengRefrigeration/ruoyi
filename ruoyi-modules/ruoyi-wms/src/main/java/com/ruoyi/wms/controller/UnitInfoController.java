package com.ruoyi.wms.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.UnitInfo;
import com.ruoyi.wms.service.IUnitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单位信息管理Controller
 * 
 * @author ruoyi
 * created on 2024-02-02
 */
@RestController
@RequestMapping("/UnitInfo")
public class UnitInfoController extends BaseController
{
    @Autowired
    private IUnitInfoService unitInfoService;

    /**
     * 查询单位信息管理列表
     */
    @RequiresPermissions("wms:UnitInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(UnitInfo unitInfo)
    {
        startPage();
        List<UnitInfo> list = unitInfoService.selectUnitInfoList(unitInfo);
        return getDataTable(list);
    }

    //TODO 如果要启用导出功能，需要在domain实体类的字段上添加注解：@com.ruoyi.common.core.annotation.Excel(name = "字段名")
    /*
     * 导出单位信息管理列表
     */
//    @RequiresPermissions("wms:UnitInfo:export")
//    @Log(title = "单位信息管理", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, UnitInfo unitInfo)
//    {
//        List<UnitInfo> list = unitInfoService.selectUnitInfoList(unitInfo);
//        ExcelUtil<UnitInfo> util = new ExcelUtil<>(UnitInfo.class);
//        util.exportExcel(response, list, "单位信息管理数据");
//    }

    /**
     * 获取单位信息管理详细信息
     */
    @RequiresPermissions("wms:UnitInfo:query")
    @GetMapping(value = "/{orgCd}")
    public AjaxResult getInfo(@PathVariable("orgCd") String orgCd)
    {
        return success(unitInfoService.selectUnitInfoByOrgCd(orgCd));
    }

    /**
     * 新增单位信息管理
     */
    @RequiresPermissions("wms:UnitInfo:add")
    @Log(title = "单位信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UnitInfo unitInfo)
    {
        return toAjax(unitInfoService.insertUnitInfo(unitInfo));
    }

    /**
     * 修改单位信息管理
     */
    @RequiresPermissions("wms:UnitInfo:edit")
    @Log(title = "单位信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UnitInfo unitInfo)
    {
        return toAjax(unitInfoService.updateUnitInfo(unitInfo));
    }

    /**
     * 删除单位信息管理
     */
    @RequiresPermissions("wms:UnitInfo:remove")
    @Log(title = "单位信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgCds}")
    public AjaxResult remove(@PathVariable String[] orgCds)
    {
        return toAjax(unitInfoService.deleteUnitInfoByOrgCds(orgCds));
    }
}
