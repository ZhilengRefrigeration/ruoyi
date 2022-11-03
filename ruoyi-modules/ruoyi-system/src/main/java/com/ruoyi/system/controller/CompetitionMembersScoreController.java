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
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.service.ICompetitionMembersScoreService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-赛程-人员得分Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionMemberScore")
public class CompetitionMembersScoreController extends BaseController
{
    @Autowired
    private ICompetitionMembersScoreService competitionMembersScoreService;

    /**
     * 查询赛会中-赛程-人员得分列表
     */
    @RequiresPermissions("system:competitionMemberScore:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionMembersScore competitionMembersScore)
    {
        startPage();
        List<CompetitionMembersScore> list = competitionMembersScoreService.selectCompetitionMembersScoreList(competitionMembersScore);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-赛程-人员得分列表
     */
    @RequiresPermissions("system:competitionMemberScore:export")
    @Log(title = "赛会中-赛程-人员得分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionMembersScore competitionMembersScore)
    {
        List<CompetitionMembersScore> list = competitionMembersScoreService.selectCompetitionMembersScoreList(competitionMembersScore);
        ExcelUtil<CompetitionMembersScore> util = new ExcelUtil<CompetitionMembersScore>(CompetitionMembersScore.class);
        util.exportExcel(response, list, "赛会中-赛程-人员得分数据");
    }

    /**
     * 获取赛会中-赛程-人员得分详细信息
     */
    @RequiresPermissions("system:competitionMemberScore:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionMembersScoreService.selectCompetitionMembersScoreById(id));
    }

    /**
     * 新增赛会中-赛程-人员得分
     */
    @RequiresPermissions("system:competitionMemberScore:add")
    @Log(title = "赛会中-赛程-人员得分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionMembersScore competitionMembersScore)
    {
        return toAjax(competitionMembersScoreService.insertCompetitionMembersScore(competitionMembersScore));
    }

    /**
     * 修改赛会中-赛程-人员得分
     */
    @RequiresPermissions("system:competitionMemberScore:edit")
    @Log(title = "赛会中-赛程-人员得分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionMembersScore competitionMembersScore)
    {
        return toAjax(competitionMembersScoreService.updateCompetitionMembersScore(competitionMembersScore));
    }

    /**
     * 删除赛会中-赛程-人员得分
     */
    @RequiresPermissions("system:competitionMemberScore:remove")
    @Log(title = "赛会中-赛程-人员得分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionMembersScoreService.deleteCompetitionMembersScoreByIds(ids));
    }
}
