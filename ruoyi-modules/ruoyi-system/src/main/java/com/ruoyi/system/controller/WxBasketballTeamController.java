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
import com.ruoyi.system.domain.WxBasketballTeam;
import com.ruoyi.system.service.IWxBasketballTeamService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 球队管理Controller
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/basketBallTeam")
public class WxBasketballTeamController extends BaseController
{
    @Autowired
    private IWxBasketballTeamService wxBasketballTeamService;

    /**
     * 查询球队管理列表
     */
    @RequiresPermissions("system:basketBallTeam:list")
    @GetMapping("/list")
    public TableDataInfo list(WxBasketballTeam wxBasketballTeam)
    {
        startPage();
        List<WxBasketballTeam> list = wxBasketballTeamService.selectWxBasketballTeamList(wxBasketballTeam);
        return getDataTable(list);
    }

    /**
     * 导出球队管理列表
     */
    @RequiresPermissions("system:basketBallTeam:export")
    @Log(title = "球队管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxBasketballTeam wxBasketballTeam)
    {
        List<WxBasketballTeam> list = wxBasketballTeamService.selectWxBasketballTeamList(wxBasketballTeam);
        ExcelUtil<WxBasketballTeam> util = new ExcelUtil<WxBasketballTeam>(WxBasketballTeam.class);
        util.exportExcel(response, list, "球队管理数据");
    }

    /**
     * 获取球队管理详细信息
     */
    @RequiresPermissions("system:basketBallTeam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxBasketballTeamService.selectWxBasketballTeamById(id));
    }

    /**
     * 新增球队管理
     */
    @RequiresPermissions("system:basketBallTeam:add")
    @Log(title = "球队管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxBasketballTeam wxBasketballTeam)
    {
        return toAjax(wxBasketballTeamService.insertWxBasketballTeam(wxBasketballTeam));
    }

    /**
     * 修改球队管理
     */
    @RequiresPermissions("system:basketBallTeam:edit")
    @Log(title = "球队管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxBasketballTeam wxBasketballTeam)
    {
        return toAjax(wxBasketballTeamService.updateWxBasketballTeam(wxBasketballTeam));
    }

    /**
     * 删除球队管理
     */
    @RequiresPermissions("system:basketBallTeam:remove")
    @Log(title = "球队管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxBasketballTeamService.deleteWxBasketballTeamByIds(ids));
    }
}