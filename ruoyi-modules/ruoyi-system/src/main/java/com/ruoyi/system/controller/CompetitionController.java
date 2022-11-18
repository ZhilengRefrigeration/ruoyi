package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
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
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.service.ICompetitionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 比赛信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController extends BaseController
{
    @Autowired
    private ICompetitionService competitionService;

    /**
     * 查询比赛信息列表
     */
    @RequiresPermissions("system:competition:list")
    @GetMapping("/list")
    public TableDataInfo list(Competition competition)
    {
        startPage();
        List<Competition> list = competitionService.selectCompetitionList(competition);
        return getDataTable(list);
    }

    /**
     * 导出比赛信息列表
     */
    @RequiresPermissions("system:competition:export")
    @Log(title = "比赛信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competition competition)
    {
        List<Competition> list = competitionService.selectCompetitionList(competition);
        ExcelUtil<Competition> util = new ExcelUtil<Competition>(Competition.class);
        util.exportExcel(response, list, "比赛信息数据");
    }

    /**
     * 获取比赛信息详细信息
     */
    @RequiresPermissions("system:competition:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionService.selectCompetitionById(id));
    }

    /**
     * 新增比赛信息
     */
    @RequiresPermissions("system:competition:add")
    @Log(title = "比赛信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competition competition)
    {
        return toAjax(competitionService.insertCompetition(competition));
    }

    @RequiresPermissions("system:competition:genCompetitionCommonAqrSpread")
    @Log(title = "生成赛会普通微信推广码", businessType = BusinessType.OTHER)
    @PostMapping("/genCompetitionCommonAqrSpread")
    public AjaxResult genCompetitionCommonAqrSpread(@RequestBody WxAppletsCodeVo wxAppletsCodeVo)
    {
        return AjaxResult.success(competitionService.genCompetitionCommonAqrSpread(wxAppletsCodeVo));
    }

    /**
     * 修改比赛信息
     */
    @RequiresPermissions("system:competition:edit")
    @Log(title = "比赛信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competition competition)
    {
        return toAjax(competitionService.updateCompetition(competition));
    }

    /**
     * 删除比赛信息
     */
    @RequiresPermissions("system:competition:remove")
    @Log(title = "比赛信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionService.deleteCompetitionByIds(ids));
    }
}
