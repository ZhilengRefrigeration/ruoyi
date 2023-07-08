package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.GlobalAttachment;
import com.ruoyi.system.service.IGlobalAttachmentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/globalAttachment")
public class GlobalAttachmentController extends BaseController
{
    @Autowired
    private IGlobalAttachmentService globalAttachmentService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:attachment:list")
    @GetMapping("/list")
    public TableDataInfo list(GlobalAttachment globalAttachment)
    {
        startPage();
        List<GlobalAttachment> list = globalAttachmentService.selectGlobalAttachmentList(globalAttachment);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:attachment:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GlobalAttachment globalAttachment)
    {
        List<GlobalAttachment> list = globalAttachmentService.selectGlobalAttachmentList(globalAttachment);
        ExcelUtil<GlobalAttachment> util = new ExcelUtil<GlobalAttachment>(GlobalAttachment.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:attachment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(globalAttachmentService.selectGlobalAttachmentById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:attachment:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GlobalAttachment globalAttachment)
    {
        return toAjax(globalAttachmentService.insertGlobalAttachment(globalAttachment));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:attachment:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GlobalAttachment globalAttachment)
    {
        return toAjax(globalAttachmentService.updateGlobalAttachment(globalAttachment));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:attachment:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(globalAttachmentService.deleteGlobalAttachmentByIds(ids));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"查询首页图片")
    @PostMapping("/getBanner")
    @ResponseBody
    public TableDataInfo getBanner(@RequestBody GlobalAttachment entity) throws Exception {
        List<GlobalAttachment> list = globalAttachmentService.selectGlobalAttachmentList(entity);
        return getDataTable(list);
    }
}
