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
import com.ruoyi.system.domain.UserBuildingRel;
import com.ruoyi.system.service.IUserBuildingRelService;
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
@RequestMapping("/rel")
public class UserBuildingRelController extends BaseController
{
    @Autowired
    private IUserBuildingRelService userBuildingRelService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:rel:list")
    @GetMapping("/list")
    public TableDataInfo list(UserBuildingRel userBuildingRel)
    {
        startPage();
        List<UserBuildingRel> list = userBuildingRelService.selectUserBuildingRelList(userBuildingRel);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:rel:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBuildingRel userBuildingRel)
    {
        List<UserBuildingRel> list = userBuildingRelService.selectUserBuildingRelList(userBuildingRel);
        ExcelUtil<UserBuildingRel> util = new ExcelUtil<UserBuildingRel>(UserBuildingRel.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:rel:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userBuildingRelService.selectUserBuildingRelById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:rel:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBuildingRel userBuildingRel)
    {
        return toAjax(userBuildingRelService.insertUserBuildingRel(userBuildingRel));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:rel:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBuildingRel userBuildingRel)
    {
        return toAjax(userBuildingRelService.updateUserBuildingRel(userBuildingRel));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:rel:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userBuildingRelService.deleteUserBuildingRelByIds(ids));
    }
}
