package com.bwie.user.controller;

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
import com.bwie.user.domain.TbPerm;
import com.bwie.user.service.ITbPermService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 权限Controller
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/perm")
public class TbPermController extends BaseController
{
    @Autowired
    private ITbPermService tbPermService;

    /**
     * 查询权限列表
     */
    @RequiresPermissions("perm:perm:list")
    @GetMapping("/list")
    public TableDataInfo list(TbPerm tbPerm)
    {
        startPage();
        List<TbPerm> list = tbPermService.selectTbPermList(tbPerm);
        return getDataTable(list);
    }

    /**
     * 导出权限列表
     */
    @RequiresPermissions("perm:perm:export")
    @Log(title = "权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbPerm tbPerm)
    {
        List<TbPerm> list = tbPermService.selectTbPermList(tbPerm);
        ExcelUtil<TbPerm> util = new ExcelUtil<TbPerm>(TbPerm.class);
        util.exportExcel(response, list, "权限数据");
    }

    /**
     * 获取权限详细信息
     */
    @RequiresPermissions("perm:perm:query")
    @GetMapping(value = "/{permId}")
    public AjaxResult getInfo(@PathVariable("permId") Long permId)
    {
        return success(tbPermService.selectTbPermByPermId(permId));
    }

    /**
     * 新增权限
     */
    @RequiresPermissions("perm:perm:add")
    @Log(title = "权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbPerm tbPerm)
    {
        return toAjax(tbPermService.insertTbPerm(tbPerm));
    }

    /**
     * 修改权限
     */
    @RequiresPermissions("perm:perm:edit")
    @Log(title = "权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbPerm tbPerm)
    {
        return toAjax(tbPermService.updateTbPerm(tbPerm));
    }

    /**
     * 删除权限
     */
    @RequiresPermissions("perm:perm:remove")
    @Log(title = "权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{permIds}")
    public AjaxResult remove(@PathVariable Long[] permIds)
    {
        return toAjax(tbPermService.deleteTbPermByPermIds(permIds));
    }
}
