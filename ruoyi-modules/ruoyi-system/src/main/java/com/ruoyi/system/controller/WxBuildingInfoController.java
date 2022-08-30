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
import com.ruoyi.system.domain.WxBuildingInfo;
import com.ruoyi.system.service.IWxBuildingInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 球场管理Controller
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/WxBuilding")
public class WxBuildingInfoController extends BaseController
{
    @Autowired
    private IWxBuildingInfoService wxBuildingInfoService;

    /**
     * 查询球场管理列表
     */
    @RequiresPermissions("system:WxBuilding:list")
    @GetMapping("/list")
    public TableDataInfo list(WxBuildingInfo wxBuildingInfo)
    {
        startPage();
        List<WxBuildingInfo> list = wxBuildingInfoService.selectWxBuildingInfoList(wxBuildingInfo);
        return getDataTable(list);
    }

    /**
     * 导出球场管理列表
     */
    @RequiresPermissions("system:WxBuilding:export")
    @Log(title = "球场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxBuildingInfo wxBuildingInfo)
    {
        List<WxBuildingInfo> list = wxBuildingInfoService.selectWxBuildingInfoList(wxBuildingInfo);
        ExcelUtil<WxBuildingInfo> util = new ExcelUtil<WxBuildingInfo>(WxBuildingInfo.class);
        util.exportExcel(response, list, "球场管理数据");
    }

    /**
     * 获取球场管理详细信息
     */
    @RequiresPermissions("system:WxBuilding:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxBuildingInfoService.selectWxBuildingInfoById(id));
    }

    /**
     * 新增球场管理
     */
    @RequiresPermissions("system:WxBuilding:add")
    @Log(title = "球场管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxBuildingInfo wxBuildingInfo)
    {
        return toAjax(wxBuildingInfoService.insertWxBuildingInfo(wxBuildingInfo));
    }

    /**
     * 修改球场管理
     */
    @RequiresPermissions("system:WxBuilding:edit")
    @Log(title = "球场管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxBuildingInfo wxBuildingInfo)
    {
        return toAjax(wxBuildingInfoService.updateWxBuildingInfo(wxBuildingInfo));
    }

    /**
     * 删除球场管理
     */
    @RequiresPermissions("system:WxBuilding:remove")
    @Log(title = "球场管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxBuildingInfoService.deleteWxBuildingInfoByIds(ids));
    }
}
