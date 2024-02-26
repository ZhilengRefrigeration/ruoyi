package com.ruoyi.wms.controller.stock;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.wms.domain.InvTransHis;
import com.ruoyi.wms.service.stock.IInvTransHisService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入出库履历Controller
 *
 * @author ryas
 * created on 2024-02-23
 */
@RestController
@RequestMapping("/InvTransHis")
public class InvTransHisController extends BaseController {
    @Autowired
    private IInvTransHisService invTransHisService;

    /**
     * 查询入出库履历列表
     */
    @RequiresPermissions("wms:InvTransHis:list")
    @GetMapping("/list")
    public TableDataInfo list(InvTransHis invTransHis) {
        startPage();
        List<InvTransHis> list = invTransHisService.selectInvTransHisList(invTransHis);
        return getDataTable(list);
    }

    /**
     * 导出入出库履历列表
     */
    @RequiresPermissions("wms:InvTransHis:export")
    @Log(title = "入出库履历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InvTransHis invTransHis) {
        List<InvTransHis> list = invTransHisService.selectInvTransHisList(invTransHis);
        if (list.isEmpty()) {
            responseJsonWarn(response, "没有数据可以导出");
            return;
        }
        ExcelUtil<InvTransHis> util = new ExcelUtil<>(InvTransHis.class);
        util.exportExcel(response, list, "入出库履历数据");
    }

    /**
     * 获取入出库履历详细信息
     */
    @RequiresPermissions("wms:InvTransHis:query")
    @GetMapping(value = "/{invTransNo}")
    public AjaxResult getInfo(@PathVariable("invTransNo") String invTransNo) {
        return success(invTransHisService.selectInvTransHisByInvTransNo(invTransNo));
    }

    /**
     * 删除入出库履历
     */
    @RequiresPermissions("wms:InvTransHis:remove")
    @Log(title = "入出库履历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{invTransNos}")
    public AjaxResult remove(@PathVariable String[] invTransNos) {
        return toAjax(invTransHisService.deleteInvTransHisByInvTransNos(invTransNos));
    }
}
