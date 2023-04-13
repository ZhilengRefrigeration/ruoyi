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
import com.bwie.user.domain.TbRolePerm;
import com.bwie.user.service.ITbRolePermService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 角色权限Controller
 * 
 * @author xs
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/roleperm")
public class TbRolePermController extends BaseController
{
    @Autowired
    private ITbRolePermService tbRolePermService;

    /**
     * 查询角色权限列表
     */
    @RequiresPermissions("roleperm:roleperm:list")
    @GetMapping("/list")
    public TableDataInfo list(TbRolePerm tbRolePerm)
    {
        startPage();
        List<TbRolePerm> list = tbRolePermService.selectTbRolePermList(tbRolePerm);
        return getDataTable(list);
    }

    /**
     * 导出角色权限列表
     */
    @RequiresPermissions("roleperm:roleperm:export")
    @Log(title = "角色权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbRolePerm tbRolePerm)
    {
        List<TbRolePerm> list = tbRolePermService.selectTbRolePermList(tbRolePerm);
        ExcelUtil<TbRolePerm> util = new ExcelUtil<TbRolePerm>(TbRolePerm.class);
        util.exportExcel(response, list, "角色权限数据");
    }

    /**
     * 获取角色权限详细信息
     */
    @RequiresPermissions("roleperm:roleperm:query")
    @GetMapping(value = "/{rolePermId}")
    public AjaxResult getInfo(@PathVariable("rolePermId") Long rolePermId)
    {
        return success(tbRolePermService.selectTbRolePermByRolePermId(rolePermId));
    }

    /**
     * 新增角色权限
     */
    @RequiresPermissions("roleperm:roleperm:add")
    @Log(title = "角色权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbRolePerm tbRolePerm)
    {
        return toAjax(tbRolePermService.insertTbRolePerm(tbRolePerm));
    }

    /**
     * 修改角色权限
     */
    @RequiresPermissions("roleperm:roleperm:edit")
    @Log(title = "角色权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbRolePerm tbRolePerm)
    {
        return toAjax(tbRolePermService.updateTbRolePerm(tbRolePerm));
    }

    /**
     * 删除角色权限
     */
    @RequiresPermissions("roleperm:roleperm:remove")
    @Log(title = "角色权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rolePermIds}")
    public AjaxResult remove(@PathVariable Long[] rolePermIds)
    {
        return toAjax(tbRolePermService.deleteTbRolePermByRolePermIds(rolePermIds));
    }
}
