package com.xjs.oneenglish.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.service.IApiEnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 英语一言Controller
 *
 * @author xjs
 * @date 2021-12-31
 */
@RestController
@RequestMapping("/oneenglish")
public class ApiEnglishController extends BaseController {
    @Autowired
    private IApiEnglishService apiEnglishService;


    //------------------------代码自动生成-----------------------------------


    /**
     * 查询英语一言列表
     */
    @RequiresPermissions("openapi:oneenglish:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiEnglish apiEnglish) {
        startPage();
        List<ApiEnglish> list = apiEnglishService.selectApiEnglishList(apiEnglish);
        return getDataTable(list);
    }

    /**
     * 导出英语一言列表
     */
    @RequiresPermissions("openapi:oneenglish:export")
    @Log(title = "英语一言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiEnglish apiEnglish) {
        List<ApiEnglish> list = apiEnglishService.selectApiEnglishList(apiEnglish);
        ExcelUtil<ApiEnglish> util = new ExcelUtil<ApiEnglish>(ApiEnglish.class);
        util.exportExcel(response, list, "英语一言数据");
    }

    /**
     * 获取英语一言详细信息
     */
    @RequiresPermissions("openapi:oneenglish:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiEnglishService.selectApiEnglishById(id));
    }

    /**
     * 删除英语一言
     */
    @RequiresPermissions("openapi:oneenglish:remove")
    @Log(title = "英语一言", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiEnglishService.deleteApiEnglishByIds(ids));
    }
}
