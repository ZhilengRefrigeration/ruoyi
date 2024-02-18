package com.ruoyi.wms.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.GoodsType;
import com.ruoyi.wms.service.IGoodsTypeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物品类型管理Controller
 *
 * @author ryas
 * created on 2024-02-18
 */
@RestController
@RequestMapping("/GoodsType")
public class GoodsTypeController extends BaseController {
    @Autowired
    private IGoodsTypeService goodsTypeService;

    /**
     * 查询物品类型管理列表
     */
    @RequiresPermissions("wms:GoodsType:list")
    @GetMapping("/list")
    public TableDataInfo list(GoodsType goodsType) {
        startPage();
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        return getDataTable(list);
    }

    /**
     * 导出物品类型管理列表
     */
    @RequiresPermissions("wms:GoodsType:export")
    @Log(title = "物品类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsType goodsType) {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        ExcelUtil<GoodsType> util = new ExcelUtil<>(GoodsType.class);
        util.exportExcel(response, list, "物品类型管理数据");
    }

    /**
     * 获取物品类型管理详细信息
     */
    @RequiresPermissions("wms:GoodsType:query")
    @GetMapping(value = "/{goodsTypeCd}")
    public AjaxResult getInfo(@PathVariable("goodsTypeCd") String goodsTypeCd) {
        return success(goodsTypeService.selectGoodsTypeByGoodsTypeCd(goodsTypeCd));
    }

    /**
     * 新增物品类型管理
     */
    @RequiresPermissions("wms:GoodsType:add")
    @Log(title = "物品类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsType goodsType) {
        return toAjax(goodsTypeService.insertGoodsType(goodsType));
    }

    /**
     * 修改物品类型管理
     */
    @RequiresPermissions("wms:GoodsType:edit")
    @Log(title = "物品类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsType goodsType) {
        return toAjax(goodsTypeService.updateGoodsType(goodsType));
    }

    /**
     * 删除物品类型管理
     */
    @RequiresPermissions("wms:GoodsType:remove")
    @Log(title = "物品类型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{goodsTypeCds}")
    public AjaxResult remove(@PathVariable String[] goodsTypeCds) {
        return toAjax(goodsTypeService.deleteGoodsTypeByGoodsTypeCds(goodsTypeCds));
    }
}
