package com.ruoyi.wms.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.ItemType;
import com.ruoyi.wms.service.IItemTypeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物品类型Controller
 *
 * @author ryas
 * created on 2024-02-20
 */
@RestController
@RequestMapping("/ItemType")
public class ItemTypeController extends BaseController {
    @Autowired
    private IItemTypeService itemTypeService;

    /**
     * 查询物品类型列表
     */
    @RequiresPermissions("wms:ItemType:list")
    @GetMapping("/list")
    public TableDataInfo list(ItemType itemType) {
        startPage();
        List<ItemType> list = itemTypeService.selectItemTypeList(itemType);
        return getDataTable(list);
    }

    /**
     * 导出物品类型列表
     */
    @RequiresPermissions("wms:ItemType:export")
    @Log(title = "物品类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemType itemType) {
        List<ItemType> list = itemTypeService.selectItemTypeList(itemType);
        ExcelUtil<ItemType> util = new ExcelUtil<>(ItemType.class);
        util.exportExcel(response, list, "物品类型数据");
    }

    /**
     * 获取物品类型详细信息
     */
    @RequiresPermissions("wms:ItemType:query")
    @GetMapping(value = "/{itemTypeCd}")
    public AjaxResult getInfo(@PathVariable("itemTypeCd") String itemTypeCd) {
        return success(itemTypeService.selectItemTypeByItemTypeCd(itemTypeCd));
    }

    /**
     * 新增物品类型
     */
    @RequiresPermissions("wms:ItemType:add")
    @Log(title = "物品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemType itemType) {
        return toAjax(itemTypeService.insertItemType(itemType));
    }

    /**
     * 修改物品类型
     */
    @RequiresPermissions("wms:ItemType:edit")
    @Log(title = "物品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemType itemType) {
        return toAjax(itemTypeService.updateItemType(itemType));
    }

    /**
     * 删除物品类型
     */
    @RequiresPermissions("wms:ItemType:remove")
    @Log(title = "物品类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemTypeCds}")
    public AjaxResult remove(@PathVariable String[] itemTypeCds) {
        return toAjax(itemTypeService.deleteItemTypeByItemTypeCds(itemTypeCds));
    }
}
