package com.ruoyi.wms.controller.stock;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.service.stock.IBaseStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基本库存Controller
 *
 * @author ryas
 * created on 2024-02-22
 */
@RestController
@RequestMapping("/BaseStock")
public class BaseStockController extends BaseController {
    @Autowired
    private IBaseStockService baseStockService;

    /**
     * 查询基本库存列表
     */
    @RequiresPermissions("wms:BaseStock:list")
    @GetMapping("/list")
    public TableDataInfo list(BaseStock baseStock) {
        startPage();
        List<BaseStock> list = baseStockService.selectBaseStockList(baseStock);
        return getDataTable(list);
    }

    //TODO 如果要启用导出功能，需要在domain实体类的字段上添加注解：@com.ruoyi.common.core.annotation.Excel(name = "字段名")
    /*
     * 导出基本库存列表
     */
//    @RequiresPermissions("wms:BaseStock:export")
//    @Log(title = "基本库存", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, BaseStock baseStock)
//    {
//        List<BaseStock> list = baseStockService.selectBaseStockList(baseStock);
//        if (list.isEmpty()) {
//            responseJsonWarn(response, "没有数据可以导出");
//            return;
//        }
//        ExcelUtil<BaseStock> util = new ExcelUtil<>(BaseStock.class);
//        util.exportExcel(response, list, "基本库存数据");
//    }

    /**
     * 获取基本库存详细信息
     */
    @RequiresPermissions("wms:BaseStock:query")
    @GetMapping(value = "/{whsCd}")
    public AjaxResult getInfo(@PathVariable("whsCd") String whsCd) {
        return success(baseStockService.selectBaseStockByWhsCd(whsCd));
    }

}
