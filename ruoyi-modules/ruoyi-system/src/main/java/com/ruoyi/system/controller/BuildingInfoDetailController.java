package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.BuildingInfoDetail;
import com.ruoyi.system.service.IBuildingInfoDetailService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@RestController
@RequestMapping("/buildingInfoDetail")
public class BuildingInfoDetailController extends BaseController
{
    @Autowired
    private IBuildingInfoDetailService buildingInfoDetailService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:detail:list")
    @GetMapping("/list")
    public TableDataInfo list(BuildingInfoDetail buildingInfoDetail)
    {
        startPage();
        List<BuildingInfoDetail> list = buildingInfoDetailService.selectBuildingInfoDetailList(buildingInfoDetail);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:detail:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BuildingInfoDetail buildingInfoDetail)
    {
        List<BuildingInfoDetail> list = buildingInfoDetailService.selectBuildingInfoDetailList(buildingInfoDetail);
        ExcelUtil<BuildingInfoDetail> util = new ExcelUtil<BuildingInfoDetail>(BuildingInfoDetail.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:detail:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(buildingInfoDetailService.selectBuildingInfoDetailById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:detail:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BuildingInfoDetail buildingInfoDetail)
    {
        return toAjax(buildingInfoDetailService.insertBuildingInfoDetail(buildingInfoDetail));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:detail:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BuildingInfoDetail buildingInfoDetail)
    {
        return toAjax(buildingInfoDetailService.updateBuildingInfoDetail(buildingInfoDetail));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:detail:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(buildingInfoDetailService.deleteBuildingInfoDetailByIds(ids));
    }
}