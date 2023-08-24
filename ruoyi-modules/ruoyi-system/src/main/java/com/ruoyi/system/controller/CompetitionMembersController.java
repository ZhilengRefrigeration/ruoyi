package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.vo.CompetitionMembersVo;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.service.ICompetitionMembersService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 比赛参与人员Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionMembers")
public class CompetitionMembersController extends BaseController
{
    @Autowired
    private ICompetitionMembersService competitionMembersService;

    /**
     * 查询比赛参与人员列表
     */
    @RequiresPermissions("system:competitionMembers:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionMembers competitionMembers)
    {
        startPage();
        List<CompetitionMembers> list = competitionMembersService.selectCompetitionMembersList(competitionMembers);
        return getDataTable(list);
    }

    /**
     * 导出比赛参与人员列表
     */
    @RequiresPermissions("system:competitionMembers:export")
    @Log(title = "比赛参与人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionMembers competitionMembers)
    {
        List<CompetitionMembers> list = competitionMembersService.selectCompetitionMembersList(competitionMembers);
        ExcelUtil<CompetitionMembers> util = new ExcelUtil<CompetitionMembers>(CompetitionMembers.class);
        util.exportExcel(response, list, "比赛参与人员数据");
    }

    /**
     * 获取比赛参与人员详细信息
     */
    @RequiresPermissions("system:competitionMembers:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionMembersService.selectCompetitionMembersById(id));
    }
    @ApiOperation("获取赛会中个人得分数据")
    @PostMapping( "/getCompetitionUserScoreInfo")
    @ResponseBody
    public AjaxResult getCompetitionUserScoreInfo(@RequestBody CompetitionMembersVo entity){
        return AjaxResult.success(competitionMembersService.getCompetitionUserScoreInfo(entity));
    }
    /**
     * 新增比赛参与人员
     */
    @RequiresPermissions("system:competitionMembers:add")
    @Log(title = "比赛参与人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionMembers competitionMembers)
    {
        return toAjax(competitionMembersService.insertCompetitionMembers(competitionMembers));
    }

    /**
     * 修改比赛参与人员
     */
    @RequiresPermissions("system:competitionMembers:edit")
    @Log(title = "比赛参与人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionMembers competitionMembers)
    {
        return toAjax(competitionMembersService.updateCompetitionMembers(competitionMembers));
    }

    /**
     * 删除比赛参与人员
     */
    @RequiresPermissions("system:competitionMembers:remove")
    @Log(title = "比赛参与人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionMembersService.deleteCompetitionMembersByIds(ids));
    }
    @PostMapping("/getJoinCompetitionMembersPage")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取参与比赛的人员列表")
    public TableDataInfo getJoinCompetitionMembersPage(@RequestBody CompetitionMembersVo entity){
        startPage();
        List<CompetitionMembersVo> list = competitionMembersService.getJoinCompetitionMembersPage(entity);
        return getDataTable(list);
    }
}
