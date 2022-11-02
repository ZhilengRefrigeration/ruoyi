package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.service.ICompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 吴一博
 * @date 2022年11月02日 15:43
 * @Description
 */
/**
 * 约战Controller
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/vs")
public class CompetitionVsController extends BaseController {
    @Autowired
    private ICompetitionService competitionService;

    /**
     * 查询约战列表
     */
    @RequiresPermissions("system:vs:list")
    @GetMapping("/list")
    public TableDataInfo list(Competition competition)
    {
        startPage();
        List<Competition> list = competitionService.selectCompetitionList(competition);
        return getDataTable(list);
    }

    /**
     * 导出约战列表
     */
    @RequiresPermissions("system:vs:export")
    @Log(title = "约战", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competition competition)
    {
        List<Competition> list = competitionService.selectCompetitionList(competition);
        ExcelUtil<Competition> util = new ExcelUtil<Competition>(Competition.class);
        util.exportExcel(response, list, "约战数据");
    }

    /**
     * 获取约战详细信息
     */
    @RequiresPermissions("system:vs:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionService.selectCompetitionById(id));
    }

    /**
     * 新增约战
     */
    @RequiresPermissions("system:vs:add")
    @Log(title = "约战", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competition competition)
    {
        return toAjax(competitionService.insertCompetition(competition));
    }

    /**
     * 修改约战
     */
    @RequiresPermissions("system:vs:edit")
    @Log(title = "约战", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competition competition)
    {
        return toAjax(competitionService.updateCompetition(competition));
    }

    /**
     * 删除约战
     */
    @RequiresPermissions("system:vs:remove")
    @Log(title = "约战", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionService.deleteCompetitionByIds(ids));
    }
}
