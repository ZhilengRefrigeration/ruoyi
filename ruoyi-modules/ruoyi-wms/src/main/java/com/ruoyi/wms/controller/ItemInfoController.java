package com.ruoyi.wms.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.ItemInfo;
import com.ruoyi.wms.service.IItemInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物品基础信息Controller
 *
 * @author ryas
 * created on 2024-02-20
 */
@RestController
@RequestMapping("/ItemInfo")
public class ItemInfoController extends BaseController {
    @Autowired
    private IItemInfoService itemInfoService;

    /**
     * 查询物品基础信息列表
     */
    @RequiresPermissions("wms:ItemInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(ItemInfo itemInfo) {
        startPage();
        List<ItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
        return getDataTable(list);
    }

    /**
     * 导出物品基础信息列表
     */
    @RequiresPermissions("wms:ItemInfo:export")
    @Log(title = "物品基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemInfo itemInfo) {
        List<ItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
        if (list.isEmpty()) {
            responseJsonWarn(response, "没有数据可以导出");
            return;
        }
        ExcelUtil<ItemInfo> util = new ExcelUtil<>(ItemInfo.class);
        util.exportExcel(response, list, "物品基础信息数据");
    }

    /**
     * 获取物品基础信息详细信息
     */
    @RequiresPermissions("wms:ItemInfo:query")
    @GetMapping(value = "/{itemCd}")
    public AjaxResult getInfo(@PathVariable("itemCd") String itemCd) {
        return success(itemInfoService.selectItemInfoByItemCd(itemCd));
    }

    /**
     * 新增物品基础信息
     */
    @RequiresPermissions("wms:ItemInfo:add")
    @Log(title = "物品基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemInfo itemInfo) {
        return itemInfoService.insertItemInfo(itemInfo);
    }

    /**
     * 新增物品基础信息（带图片文件一起提交）
     */
    @RequiresPermissions("wms:ItemInfo:add")
    @Log(title = "物品基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/addWithImage")
    public AjaxResult addWithImage(ItemInfo itemInfo) {
        return itemInfoService.insertItemInfo(itemInfo);
    }

    /**
     * 修改物品基础信息
     */
    @RequiresPermissions("wms:ItemInfo:edit")
    @Log(title = "物品基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemInfo itemInfo) {
        return itemInfoService.updateItemInfo(itemInfo);
    }

    /**
     * 修改物品基础信息（带图片文件一起提交）
     */
    @RequiresPermissions("wms:ItemInfo:edit")
    @Log(title = "物品基础信息", businessType = BusinessType.UPDATE)
    @PutMapping("/editWithImage")
    public AjaxResult editWithImage(ItemInfo itemInfo) {
        return itemInfoService.updateItemInfo(itemInfo);
    }

    /**
     * 删除物品基础信息
     */
    @RequiresPermissions("wms:ItemInfo:remove")
    @Log(title = "物品基础信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemCds}")
    public AjaxResult remove(@PathVariable String[] itemCds) {
        return toAjax(itemInfoService.deleteItemInfoByItemCds(itemCds));
    }
}
