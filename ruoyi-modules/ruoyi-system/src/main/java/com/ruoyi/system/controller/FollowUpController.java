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
import com.ruoyi.system.domain.FollowUp;
import com.ruoyi.system.service.IFollowUpService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 跟进模块-客户跟进记录Controller
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
@RestController
@RequestMapping("/up")
public class FollowUpController extends BaseController
{
    @Autowired
    private IFollowUpService followUpService;

    /**
     * 查询跟进模块-客户跟进记录列表
     */
    @RequiresPermissions("system:up:list")
    @GetMapping("/list")
    public TableDataInfo list(FollowUp followUp)
    {
        startPage();
        List<FollowUp> list = followUpService.selectFollowUpList(followUp);
        return getDataTable(list);
    }

    /**
     * 导出跟进模块-客户跟进记录列表
     */
    @RequiresPermissions("system:up:export")
    @Log(title = "跟进模块-客户跟进记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FollowUp followUp)
    {
        List<FollowUp> list = followUpService.selectFollowUpList(followUp);
        ExcelUtil<FollowUp> util = new ExcelUtil<FollowUp>(FollowUp.class);
        util.exportExcel(response, list, "跟进模块-客户跟进记录数据");
    }

    /**
     * 获取跟进模块-客户跟进记录详细信息
     */
    @RequiresPermissions("system:up:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(followUpService.selectFollowUpById(id));
    }

    /**
     * 新增跟进模块-客户跟进记录
     */
    @RequiresPermissions("system:up:add")
    @Log(title = "跟进模块-客户跟进记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FollowUp followUp)
    {
        return toAjax(followUpService.save(followUp));
    }

    /**
     * 修改跟进模块-客户跟进记录
     */
    @RequiresPermissions("system:up:edit")
    @Log(title = "跟进模块-客户跟进记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FollowUp followUp)
    {
        return toAjax(followUpService.updateFollowUp(followUp));
    }

    /**
     * 删除跟进模块-客户跟进记录
     */
    @RequiresPermissions("system:up:remove")
    @Log(title = "跟进模块-客户跟进记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(followUpService.deleteFollowUpByIds(ids));
    }
}
