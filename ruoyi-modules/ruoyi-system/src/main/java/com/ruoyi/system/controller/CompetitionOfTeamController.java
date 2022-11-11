package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
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
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.service.ICompetitionOfTeamService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-参赛队伍Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionOfTeam")
public class CompetitionOfTeamController extends BaseController
{
    @Autowired
    private ICompetitionOfTeamService competitionOfTeamService;

    /**
     * 查询赛会中-参赛队伍列表
     */
    @RequiresPermissions("system:competitionOfTeam:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionOfTeamVo competitionOfTeam)
    {
        startPage();
        List<CompetitionOfTeamVo> list = competitionOfTeamService.selectCompetitionOfTeamList(competitionOfTeam);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-参赛队伍列表
     */
    @RequiresPermissions("system:competitionOfTeam:export")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionOfTeam competitionOfTeam)
    {
        List<CompetitionOfTeamVo> list = competitionOfTeamService.selectCompetitionOfTeamList(competitionOfTeam);
        ExcelUtil<CompetitionOfTeamVo> util = new ExcelUtil<CompetitionOfTeamVo>(CompetitionOfTeamVo.class);
        util.exportExcel(response, list, "赛会中-参赛队伍数据");
    }

    /**
     * 获取赛会中-参赛队伍详细信息
     */
    @RequiresPermissions("system:competitionOfTeam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionOfTeamService.selectCompetitionOfTeamById(id));
    }

    /**
     * 新增赛会中-参赛队伍
     */
    @RequiresPermissions("system:competitionOfTeam:add")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionOfTeam competitionOfTeam)
    {
        return toAjax(competitionOfTeamService.insertCompetitionOfTeam(competitionOfTeam));
    }

    /**
     * 修改赛会中-参赛队伍
     */
    @RequiresPermissions("system:competitionOfTeam:edit")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionOfTeam competitionOfTeam)
    {
        return toAjax(competitionOfTeamService.updateCompetitionOfTeam(competitionOfTeam));
    }
    @RequiresPermissions("system:competitionOfTeam:batchEditById")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult batchEditById(@RequestBody List<CompetitionOfTeam> list)
    {
        return toAjax(competitionOfTeamService.batchUpdateCompetitionOfTeam(list));
    }
    /**
     * 删除赛会中-参赛队伍
     */
    @RequiresPermissions("system:competitionOfTeam:remove")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionOfTeamService.deleteCompetitionOfTeamByIds(ids));
    }
}
