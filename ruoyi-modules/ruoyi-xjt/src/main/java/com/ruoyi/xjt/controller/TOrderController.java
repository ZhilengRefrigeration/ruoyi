package com.ruoyi.xjt.controller;

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
import com.ruoyi.xjt.domain.TOrder;
import com.ruoyi.xjt.service.ITOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 缴费订单Controller
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@RestController
@RequestMapping("/order")
public class TOrderController extends BaseController
{
    @Autowired
    private ITOrderService tOrderService;

    /**
     * 查询缴费订单列表
     */
    @RequiresPermissions("xjt:order:list")
    @GetMapping("/list")
    public TableDataInfo list(TOrder tOrder)
    {
        startPage();
        List<TOrder> list = tOrderService.selectTOrderList(tOrder);
        return getDataTable(list);
    }

    /**
     * 导出缴费订单列表
     */
    @RequiresPermissions("xjt:order:export")
    @Log(title = "缴费订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TOrder tOrder)
    {
        List<TOrder> list = tOrderService.selectTOrderList(tOrder);
        ExcelUtil<TOrder> util = new ExcelUtil<TOrder>(TOrder.class);
        util.exportExcel(response, list, "缴费订单数据");
    }

    /**
     * 获取缴费订单详细信息
     */
    @RequiresPermissions("xjt:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tOrderService.selectTOrderById(id));
    }

    /**
     * 新增缴费订单
     */
    @RequiresPermissions("xjt:order:add")
    @Log(title = "缴费订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TOrder tOrder)
    {
        return toAjax(tOrderService.insertTOrder(tOrder));
    }

    /**
     * 修改缴费订单
     */
    @RequiresPermissions("xjt:order:edit")
    @Log(title = "缴费订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TOrder tOrder)
    {
        return toAjax(tOrderService.updateTOrder(tOrder));
    }

    /**
     * 删除缴费订单
     */
    @RequiresPermissions("xjt:order:remove")
    @Log(title = "缴费订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tOrderService.deleteTOrderByIds(ids));
    }
}
