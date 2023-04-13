package com.ruoyi.invest.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.invest.service.TbFirmService;
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
import com.ruoyi.invest.domain.TbFirm;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 企业Controller
 * 
 * @author ruoyi
 * @date 2023-01-13
 */
@RestController
@RequestMapping("/firm")
public class TbFirmController extends BaseController
{
    @Autowired
    private TbFirmService tbFirmService;

    /**
     * 查询企业列表
     */
//    @RequiresPermissions("system:firm:list")
    @GetMapping("/list")
    public TableDataInfo list(TbFirm tbFirm)
    {
        startPage();
        List<TbFirm> list = tbFirmService.selectTbFirmList(tbFirm);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
//    @RequiresPermissions("system:firm:export")
    @Log(title = "企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbFirm tbFirm)
    {
        List<TbFirm> list = tbFirmService.selectTbFirmList(tbFirm);
        ExcelUtil<TbFirm> util = new ExcelUtil<TbFirm>(TbFirm.class);
        util.exportExcel(response, list, "企业数据");
    }

    /**
     * 获取企业详细信息
     */
//    @RequiresPermissions("system:firm:query")
    @GetMapping(value = "/{firmId}")
    public AjaxResult getInfo(@PathVariable("firmId") Long firmId)
    {
        return success(tbFirmService.selectTbFirmByFirmId(firmId));
    }

    /**
     * 新增企业
     */
//    @RequiresPermissions("system:firm:add")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbFirm tbFirm)
    {
        return toAjax(tbFirmService.insertTbFirm(tbFirm));
    }

    /**
     * 修改企业
     */
//    @RequiresPermissions("system:firm:edit")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbFirm tbFirm)
    {
        return toAjax(tbFirmService.updateTbFirm(tbFirm));
    }

    /**
     * 删除企业
     */
//    @RequiresPermissions("system:firm:remove")
    @Log(title = "企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{firmIds}")
    public AjaxResult remove(@PathVariable Long[] firmIds)
    {
        return toAjax(tbFirmService.deleteTbFirmByFirmIds(firmIds));
    }
}
