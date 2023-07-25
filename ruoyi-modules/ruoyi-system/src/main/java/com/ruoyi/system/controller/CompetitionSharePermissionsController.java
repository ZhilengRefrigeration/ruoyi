package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.vo.CompetitionSharePermissionsVo;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.system.domain.CompetitionSharePermissions;
import com.ruoyi.system.service.ICompetitionSharePermissionsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会-权限分享Controller
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
@RestController
@RequestMapping("/competitionPermissions")
public class CompetitionSharePermissionsController extends BaseController
{
    @Autowired
    private ICompetitionSharePermissionsService competitionSharePermissionsService;

    /**
     * 查询赛会-权限分享列表
     */
    @RequiresPermissions("system:competitionPermissions:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionSharePermissions competitionSharePermissions)
    {
        startPage();
        List<CompetitionSharePermissions> list = competitionSharePermissionsService.selectCompetitionSharePermissionsList(competitionSharePermissions);
        return getDataTable(list);
    }

    /**
     * 导出赛会-权限分享列表
     */
    @RequiresPermissions("system:competitionPermissions:export")
    @Log(title = "赛会-权限分享", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionSharePermissions competitionSharePermissions)
    {
        List<CompetitionSharePermissions> list = competitionSharePermissionsService.selectCompetitionSharePermissionsList(competitionSharePermissions);
        ExcelUtil<CompetitionSharePermissions> util = new ExcelUtil<CompetitionSharePermissions>(CompetitionSharePermissions.class);
        util.exportExcel(response, list, "赛会-权限分享数据");
    }

    /**
     * 获取赛会-权限分享详细信息
     */
    @RequiresPermissions("system:competitionPermissions:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionSharePermissionsService.selectCompetitionSharePermissionsById(id));
    }

    /**
     * 新增赛会-权限分享
     */
    @RequiresPermissions("system:competitionPermissions:add")
    @Log(title = "赛会-权限分享", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionSharePermissions competitionSharePermissions)
    {
        return toAjax(competitionSharePermissionsService.insertCompetitionSharePermissions(competitionSharePermissions));
    }
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页查询分享数据")
    @PostMapping("/getList")
    public TableDataInfo getList(@RequestBody CompetitionSharePermissions competitionSharePermissions)
    {
        startPage();
        List<CompetitionSharePermissions> list = competitionSharePermissionsService.getList(competitionSharePermissions);
        return getDataTable(list);
    }
    /**
     * 修改赛会-权限分享
     */
    @RequiresPermissions("system:competitionPermissions:edit")
    @Log(title = "赛会-权限分享", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionSharePermissions competitionSharePermissions)
    {
        return toAjax(competitionSharePermissionsService.updateCompetitionSharePermissions(competitionSharePermissions));
    }

    /**
     * 删除赛会-权限分享
     */
    @RequiresPermissions("system:competitionPermissions:remove")
    @Log(title = "赛会-权限分享", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionSharePermissionsService.deleteCompetitionSharePermissionsByIds(ids));
    }

    @Log(title = ApiTerminal.wxMiniProgram+"赛会-批量分享赛会的控制权限", businessType = BusinessType.INSERT)
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"批量分享赛会的控制权限")
    @PostMapping("/shareCompetitionPermissions")
    public AjaxResult shareCompetitionPermissions(@RequestBody CompetitionSharePermissions vo)
    {
        return AjaxResult.success(competitionSharePermissionsService.shareCompetitionPermissions(vo));
    }
    @Log(title = ApiTerminal.wxMiniProgram+"赛会-批量删除分享赛会的控制权限",  businessType = BusinessType.DELETE)
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"批量删除分享赛会的控制权限")
    @PostMapping("/delShareCompetitionPermissions")
    public AjaxResult delShareCompetitionPermissions(@RequestBody Long[] ids)
    {
        return AjaxResult.success(competitionSharePermissionsService.delShareCompetitionPermissions(ids));
    }
}
