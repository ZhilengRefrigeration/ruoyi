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
import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.service.ICompetitionTeamGroupService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-分组Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionTeamGroup")
public class CompetitionTeamGroupController extends BaseController
{
    @Autowired
    private ICompetitionTeamGroupService competitionTeamGroupService;

    /**
     * 查询赛会中-分组列表
     */
    @RequiresPermissions("system:competitionTeamGroup:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionTeamGroup competitionTeamGroup)
    {
        startPage();
        List<CompetitionTeamGroup> list = competitionTeamGroupService.selectCompetitionTeamGroupList(competitionTeamGroup);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-分组列表
     */
    @RequiresPermissions("system:competitionTeamGroup:export")
    @Log(title = "赛会中-分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionTeamGroup competitionTeamGroup)
    {
        List<CompetitionTeamGroup> list = competitionTeamGroupService.selectCompetitionTeamGroupList(competitionTeamGroup);
        ExcelUtil<CompetitionTeamGroup> util = new ExcelUtil<CompetitionTeamGroup>(CompetitionTeamGroup.class);
        util.exportExcel(response, list, "赛会中-分组数据");
    }

    /**
     * 获取赛会中-分组详细信息
     */
    @RequiresPermissions("system:competitionTeamGroup:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionTeamGroupService.selectCompetitionTeamGroupById(id));
    }

    /**
     * 新增赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:add")
    @Log(title = "赛会中-分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionTeamGroup competitionTeamGroup)
    {
        return toAjax(competitionTeamGroupService.insertCompetitionTeamGroup(competitionTeamGroup));
    }

    /**
     * 修改赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:edit")
    @Log(title = "赛会中-分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionTeamGroup competitionTeamGroup)
    {
        return toAjax(competitionTeamGroupService.updateCompetitionTeamGroup(competitionTeamGroup));
    }

    /**
     * 删除赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:remove")
    @Log(title = "赛会中-分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionTeamGroupService.deleteCompetitionTeamGroupByIds(ids));
    }
}
