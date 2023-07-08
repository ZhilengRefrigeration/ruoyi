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
import com.ruoyi.system.domain.UserRole;
import com.ruoyi.system.service.IUserRoleService;
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
@RequestMapping("/wxUserRole")
public class UserRoleController extends BaseController
{
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    public TableDataInfo list(UserRole userRole)
    {
        startPage();
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:role:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserRole userRole)
    {
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        ExcelUtil<UserRole> util = new ExcelUtil<UserRole>(UserRole.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:role:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userRoleService.selectUserRoleById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.insertUserRole(userRole));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.updateUserRole(userRole));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:role:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userRoleService.deleteUserRoleByIds(ids));
    }
}
