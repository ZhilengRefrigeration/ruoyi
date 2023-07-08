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
import com.ruoyi.system.domain.ShArea;
import com.ruoyi.system.service.IShAreaService;
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
@RequestMapping("/shArea")
public class ShAreaController extends BaseController
{
    @Autowired
    private IShAreaService shAreaService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:area:list")
    @GetMapping("/list")
    public TableDataInfo list(ShArea shArea)
    {
        startPage();
        List<ShArea> list = shAreaService.selectShAreaList(shArea);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:area:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShArea shArea)
    {
        List<ShArea> list = shAreaService.selectShAreaList(shArea);
        ExcelUtil<ShArea> util = new ExcelUtil<ShArea>(ShArea.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:area:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shAreaService.selectShAreaById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:area:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShArea shArea)
    {
        return toAjax(shAreaService.insertShArea(shArea));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:area:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShArea shArea)
    {
        return toAjax(shAreaService.updateShArea(shArea));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:area:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shAreaService.deleteShAreaByIds(ids));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"查询离得最近的城市")
    @PostMapping("/findMyCity")
    @ResponseBody
    public TableDataInfo findMyCity(@RequestBody ShArea entity) throws Exception {
        List<ShArea> list = shAreaService.findMyCity(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"获取所有城市接口")
    @PostMapping("/findAllCity")
    @ResponseBody
    public TableDataInfo findAllCity() throws Exception {
        ShArea entity=new ShArea();
        entity.setLevel(2);
        List<ShArea> list = shAreaService.selectShAreaList(entity);
        return getDataTable(list);
    }
}
