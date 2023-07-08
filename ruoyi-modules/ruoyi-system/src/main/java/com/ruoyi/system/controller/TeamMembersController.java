package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.vo.TeamMembersResponse;
import com.ruoyi.system.domain.vo.TeamMembersVo;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.TeamMembers;
import com.ruoyi.system.service.ITeamMembersService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 球队人员Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/teamMembers")
public class TeamMembersController extends BaseController
{
    @Autowired
    private ITeamMembersService teamMembersService;

    /**
     * 查询球队人员列表
     */
    @RequiresPermissions("system:teamMembers:list")
    @GetMapping("/list")
    public TableDataInfo list(TeamMembersVo teamMembers)
    {
        startPage();
        List<TeamMembersVo> list = teamMembersService.selectTeamMembersList(teamMembers);
        return getDataTable(list);
    }

    /**
     * 导出球队人员列表
     */
    @RequiresPermissions("system:teamMembers:export")
    @Log(title = "球队人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamMembersVo teamMembers)
    {
        List<TeamMembersVo> list = teamMembersService.selectTeamMembersList(teamMembers);
        ExcelUtil<TeamMembersVo> util = new ExcelUtil<TeamMembersVo>(TeamMembersVo.class);
        util.exportExcel(response, list, "球队人员数据");
    }

    /**
     * 获取球队人员详细信息
     */
    @RequiresPermissions("system:teamMembers:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teamMembersService.selectTeamMembersById(id));
    }

    /**
     * 新增球队人员
     */
    @RequiresPermissions("system:teamMembers:add")
    @Log(title = "球队人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamMembers teamMembers)
    {
        return toAjax(teamMembersService.insertTeamMembers(teamMembers));
    }

    /**
     * 修改球队人员
     */
    @RequiresPermissions("system:teamMembers:edit")
    @Log(title = "球队人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamMembers teamMembers)
    {
        return toAjax(teamMembersService.updateTeamMembers(teamMembers));
    }

    /**
     * 删除球队人员
     */
    @RequiresPermissions("system:teamMembers:remove")
    @Log(title = "球队人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teamMembersService.deleteTeamMembersByIds(ids));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据球队ID查询加入球队的队员列表")
    @PostMapping("/getTeamMembersByTeamId")
    @ResponseBody
    public TableDataInfo getTeamMembersByTeamId(@RequestParam("teamId") Long teamId) throws Exception {
        List<TeamMembersResponse> list = teamMembersService.getTeamMembersByTeamId(teamId);
        return getDataTable(list);
    }
}
