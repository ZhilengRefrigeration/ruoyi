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
import com.bwie.user.domain.TbMyrole;
import com.bwie.user.service.ITbMyroleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 角色Controller
 * 
 * @author xs
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/myrole")
public class TbMyroleController extends BaseController
{
    @Autowired
    private ITbMyroleService tbMyroleService;

    /**
     * 查询角色列表
     */
    @RequiresPermissions("myrole:myrole:list")
    @GetMapping("/list")
    public TableDataInfo list(TbMyrole tbMyrole)
    {
        startPage();
        List<TbMyrole> list = tbMyroleService.selectTbMyroleList(tbMyrole);
        return getDataTable(list);
    }

    /**
     * 导出角色列表
     */
    @RequiresPermissions("myrole:myrole:export")
    @Log(title = "角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbMyrole tbMyrole)
    {
        List<TbMyrole> list = tbMyroleService.selectTbMyroleList(tbMyrole);
        ExcelUtil<TbMyrole> util = new ExcelUtil<TbMyrole>(TbMyrole.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 获取角色详细信息
     */
    @RequiresPermissions("myrole:myrole:query")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return success(tbMyroleService.selectTbMyroleByRoleId(roleId));
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("myrole:myrole:add")
    @Log(title = "角色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbMyrole tbMyrole)
    {
        return toAjax(tbMyroleService.insertTbMyrole(tbMyrole));
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("myrole:myrole:edit")
    @Log(title = "角色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbMyrole tbMyrole)
    {
        return toAjax(tbMyroleService.updateTbMyrole(tbMyrole));
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("myrole:myrole:remove")
    @Log(title = "角色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(tbMyroleService.deleteTbMyroleByRoleIds(roleIds));
    }
}
