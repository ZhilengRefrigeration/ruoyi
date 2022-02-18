package com.xjs.oneenglish.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import com.xjs.oneenglish.factory.OneEnglishFactory;
import com.xjs.oneenglish.service.IApiEnglishService;
import com.xjs.validation.group.SelectGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "业务模块-英语一言")
public class ApiEnglishController extends BaseController {
    @Autowired
    private IApiEnglishService apiEnglishService;
    @Autowired
    private OneEnglishFactory tianXingOneEnglishFactory;

    @RequiresLogin
    @GetMapping("/getOneEnglish")
    @ApiOperation("英语一言接口")
    @Log(title = "获取英语一言")
    public AjaxResult getOneEnglish() {
        ApiEnglish oneEnglish = tianXingOneEnglishFactory.getOneEnglish(new RequestBody());
        return AjaxResult.success(oneEnglish);
    }


    //------------------------代码自动生成-----------------------------------


    /**
     * 查询英语一言列表
     */
    @RequiresPermissions("openapi:oneenglish:list")
    @GetMapping("/list")
    @ApiOperation("查询英语一言列表")
    public TableDataInfo list(@Validated({SelectGroup.class}) ApiEnglish apiEnglish) {
        startPage();
        List<ApiEnglish> list = apiEnglishService.selectApiEnglishList(apiEnglish);
        return getDataTable(list);
    }

    /**
     * 导出英语一言列表
     */
    @RequiresPermissions("openapi:oneenglish:export")
    @Log(title = "英语一言", businessType = BusinessType.EXPORT)
    @ApiOperation("导出英语一言列表")
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
    @ApiOperation("获取英语一言详细信息")
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
    @ApiOperation("删除英语一言")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiEnglishService.deleteApiEnglishByIds(ids));
    }
}
