package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.services.domain.SysSeqRule;
import com.ruoyi.system.service.ISysSeqRuleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 序列号生成规则Controller
 *
 * @author ryas
 * created on 2024-02-20
 */
@RestController
@RequestMapping("/SeqRule")
public class SysSeqRuleController extends BaseController {
    @Autowired
    private ISysSeqRuleService sysSeqRuleService;

    /**
     * 查询序列号生成规则列表
     */
    @RequiresPermissions("system:SeqRule:list")
    @GetMapping("/list")
    public TableDataInfo list(SysSeqRule sysSeqRule) {
        startPage();
        List<SysSeqRule> list = sysSeqRuleService.selectSysSeqRuleList(sysSeqRule);
        return getDataTable(list);
    }

    /**
     * 导出序列号生成规则列表
     */
    @RequiresPermissions("system:SeqRule:export")
    @Log(title = "序列号生成规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSeqRule sysSeqRule) {
        List<SysSeqRule> list = sysSeqRuleService.selectSysSeqRuleList(sysSeqRule);
        ExcelUtil<SysSeqRule> util = new ExcelUtil<>(SysSeqRule.class);
        util.exportExcel(response, list, "序列号生成规则数据");
    }

    /**
     * 获取序列号生成规则详细信息
     */
    @RequiresPermissions("system:SeqRule:query")
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId) {
        return success(sysSeqRuleService.selectSysSeqRuleByRuleId(ruleId));
    }

    /**
     * 新增序列号生成规则
     */
    @RequiresPermissions("system:SeqRule:add")
    @Log(title = "序列号生成规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSeqRule sysSeqRule) {
        return toAjax(sysSeqRuleService.insertSysSeqRule(sysSeqRule));
    }

    /**
     * 修改序列号生成规则
     */
    @RequiresPermissions("system:SeqRule:edit")
    @Log(title = "序列号生成规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSeqRule sysSeqRule) {
        return toAjax(sysSeqRuleService.updateSysSeqRule(sysSeqRule));
    }

    /**
     * 删除序列号生成规则
     */
    @RequiresPermissions("system:SeqRule:remove")
    @Log(title = "序列号生成规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds) {
        return toAjax(sysSeqRuleService.deleteSysSeqRuleByRuleIds(ruleIds));
    }

    /**
     * 规则启用状态修改
     */
    @RequiresPermissions("system:SeqRule:edit")
    @Log(title = "序列号生成规则启用状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeRuleEnableFlag")
    public AjaxResult changeRuleEnableFlag(@RequestBody SysSeqRule sysSeqRule) {
        return toAjax(sysSeqRuleService.updateEnableFlag(sysSeqRule));
    }
}
