package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import com.ruoyi.system.domain.vo.CompetitionUnifiedRecordVo;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-球队VS球队关系Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionTeamVsTeam")
public class CompetitionTeamVsTeamController extends BaseController
{
    @Autowired
    private ICompetitionTeamVsTeamService competitionTeamVsTeamService;

    /**
     * 查询赛会中-球队VS球队关系列表
     */
    @RequiresPermissions("system:competitionTeamVsTeam:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        startPage();
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamService.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-球队VS球队关系列表
     */
    @RequiresPermissions("system:competitionTeamVsTeam:export")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamService.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
        ExcelUtil<CompetitionTeamVsTeamVo> util = new ExcelUtil<CompetitionTeamVsTeamVo>(CompetitionTeamVsTeamVo.class);
        util.exportExcel(response, list, "赛会中-球队VS球队关系数据");
    }

    /**
     * 获取赛会中-球队VS球队关系详细信息
     */
    @RequiresPermissions("system:competitionTeamVsTeam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionTeamVsTeamService.selectCompetitionTeamVsTeamById(id));
    }

    /**
     * 新增赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:add")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return toAjax(competitionTeamVsTeamService.insertCompetitionTeamVsTeam(competitionTeamVsTeam));
    }

    /**
     * 修改赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:edit")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return toAjax(competitionTeamVsTeamService.updateCompetitionTeamVsTeam(competitionTeamVsTeam));
    }

    /**
     * 删除赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:remove")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionTeamVsTeamService.deleteCompetitionTeamVsTeamByIds(ids));
    }
    @Log(title = "赛会中球队VS球队比赛结果数据", businessType = BusinessType.OTHER)
    @ApiOperation("根据ID获取当前比赛赛程的所有统分结果")
    @GetMapping("/competitionUnifiedRecord/{id}")
    public AjaxResult getCompetitionUnifiedRecordById(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionTeamVsTeamService.getCompetitionUnifiedRecordById(id));
    }

    @Log(title = "赛会中球队VS球队比赛结果数据2", businessType = BusinessType.OTHER)
    @ApiOperation("根据ID获取当前比赛赛程的所有统分结果2")
    @GetMapping("/getCompetitionVsRecordById/{id}")
    public AjaxResult getCompetitionVsRecordById(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionTeamVsTeamService.getCompetitionVsRecordById(id));
    }
}