package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.CompetitionVsRecordVo;
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
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.service.ICompetitionResultService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-赛程结果记录Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionResult")
public class CompetitionResultController extends BaseController
{
    @Autowired
    private ICompetitionResultService competitionResultService;

    /**
     * 查询赛会中-赛程结果记录列表
     */
    @RequiresPermissions("system:competitionResult:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionResult competitionResult)
    {
        startPage();
        List<CompetitionResult> list = competitionResultService.selectCompetitionResultList(competitionResult);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-赛程结果记录列表
     */
    @RequiresPermissions("system:competitionResult:export")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionResult competitionResult)
    {
        List<CompetitionResult> list = competitionResultService.selectCompetitionResultList(competitionResult);
        ExcelUtil<CompetitionResult> util = new ExcelUtil<CompetitionResult>(CompetitionResult.class);
        util.exportExcel(response, list, "赛会中赛程结果记录数据");
    }

    /**
     * 获取赛会中-赛程结果记录详细信息
     */
    @RequiresPermissions("system:competitionResult:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionResultService.selectCompetitionResultById(id));
    }

    /**
     * 新增赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:add")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionResult competitionResult)
    {
        return toAjax(competitionResultService.insertCompetitionResult(competitionResult));
    }

    /**
     * 修改赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:edit")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionResult competitionResult)
    {
        return toAjax(competitionResultService.updateCompetitionResult(competitionResult));
    }
    @RequiresPermissions("system:competitionResult:batchEdit")
    @Log(title = "赛会中批量保存赛程结果记录", businessType = BusinessType.UPDATE)
    @PutMapping("/batchEdit")
    public AjaxResult batchEdit(@RequestBody List<CompetitionResult> list)
    {
        return toAjax(competitionResultService.batchUpdateCompetitionResult(list));
    }
    @RequiresPermissions("system:competitionResult:editData")
    @Log(title = "赛会中保存赛程结果记录2", businessType = BusinessType.UPDATE)
    @PutMapping("/editData")
    public AjaxResult editData(@RequestBody CompetitionVsRecordVo obj)
    {
        return toAjax(competitionResultService.editData(obj));
    }

    /**
     * 删除赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:remove")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionResultService.deleteCompetitionResultByIds(ids));
    }
}
