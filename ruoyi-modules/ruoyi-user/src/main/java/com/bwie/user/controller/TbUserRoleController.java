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
import com.bwie.user.domain.TbUserRole;
import com.bwie.user.service.ITbUserRoleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户类型Controller
 * 
 * @author xs
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/userrole")
public class TbUserRoleController extends BaseController
{
    @Autowired
    private ITbUserRoleService tbUserRoleService;

    /**
     * 查询用户类型列表
     */
    @RequiresPermissions("userrole:userrole:list")
    @GetMapping("/list")
    public TableDataInfo list(TbUserRole tbUserRole)
    {
        startPage();
        List<TbUserRole> list = tbUserRoleService.selectTbUserRoleList(tbUserRole);
        return getDataTable(list);
    }

    /**
     * 导出用户类型列表
     */
    @RequiresPermissions("userrole:userrole:export")
    @Log(title = "用户类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbUserRole tbUserRole)
    {
        List<TbUserRole> list = tbUserRoleService.selectTbUserRoleList(tbUserRole);
        ExcelUtil<TbUserRole> util = new ExcelUtil<TbUserRole>(TbUserRole.class);
        util.exportExcel(response, list, "用户类型数据");
    }

    /**
     * 获取用户类型详细信息
     */
    @RequiresPermissions("userrole:userrole:query")
    @GetMapping(value = "/{userRoleId}")
    public AjaxResult getInfo(@PathVariable("userRoleId") Long userRoleId)
    {
        return success(tbUserRoleService.selectTbUserRoleByUserRoleId(userRoleId));
    }

    /**
     * 新增用户类型
     */
    @RequiresPermissions("userrole:userrole:add")
    @Log(title = "用户类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbUserRole tbUserRole)
    {
        return toAjax(tbUserRoleService.insertTbUserRole(tbUserRole));
    }

    /**
     * 修改用户类型
     */
    @RequiresPermissions("userrole:userrole:edit")
    @Log(title = "用户类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbUserRole tbUserRole)
    {
        return toAjax(tbUserRoleService.updateTbUserRole(tbUserRole));
    }

    /**
     * 删除用户类型
     */
    @RequiresPermissions("userrole:userrole:remove")
    @Log(title = "用户类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userRoleIds}")
    public AjaxResult remove(@PathVariable Long[] userRoleIds)
    {
        return toAjax(tbUserRoleService.deleteTbUserRoleByUserRoleIds(userRoleIds));
    }
}
