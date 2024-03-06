package com.ruoyi.wms.controller.stock;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.domain.vo.StockVo;
import com.ruoyi.wms.service.stock.IBaseStockService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 导出基本库存列表
     */
    @RequiresPermissions("wms:BaseStock:export")
    @Log(title = "基本库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseStock baseStock) {
        List<BaseStock> list = baseStockService.selectBaseStockList(baseStock);
        if (list.isEmpty()) {
            responseJsonWarn(response, "没有数据可以导出");
            return;
        }
        ExcelUtil<BaseStock> util = new ExcelUtil<>(BaseStock.class);
        util.exportExcel(response, list, "基本库存数据");
    }

    /**
     * 获取基本库存详细信息
     */
    @RequiresPermissions("wms:BaseStock:query")
    @PostMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestBody BaseStock form) {
        return success(baseStockService.selectBaseStockByPK(form.getWhsCd(), form.getStgBinCd(), form.getItemCd(), form.getLotNo(), form.getSubLotNo()));
    }

    /**
     * 入库
     */
    @RequiresPermissions("wms:BaseStock:instock")
    @PostMapping(value = "/instock")
    public AjaxResult instock(@RequestBody StockVo stockVo) throws Exception {
        return baseStockService.instock(stockVo);
    }

    /**
     * 出库
     */
    @RequiresPermissions("wms:BaseStock:outstock")
    @PostMapping(value = "/outstock")
    public AjaxResult outstock(@RequestBody StockVo stockVo) throws Exception {
        return baseStockService.outstock(stockVo);
    }

}
