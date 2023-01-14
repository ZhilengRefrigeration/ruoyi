package com.ruoyi.invest.controller;

import java.util.List;
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
import com.ruoyi.invest.domain.TbInvest;
import com.ruoyi.invest.service.TbInvestService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 投资Controller
 * 
 * @author ruoyi
 * @date 2023-01-13
 */
@RestController
@RequestMapping("/invest")
public class TbInvestController extends BaseController
{
    @Autowired
    private TbInvestService tbInvestService;

    /**
     * 查询投资列表
     */
//    @RequiresPermissions("system:invest:list")
    @GetMapping("/list")
    public TableDataInfo list(TbInvest tbInvest)
    {
        startPage();
        List<TbInvest> list = tbInvestService.selectTbInvestList(tbInvest);
        return getDataTable(list);
    }

    /**
     * 导出投资列表
     */
//    @RequiresPermissions("system:invest:export")
    @Log(title = "投资", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbInvest tbInvest)
    {
        List<TbInvest> list = tbInvestService.selectTbInvestList(tbInvest);
        ExcelUtil<TbInvest> util = new ExcelUtil<TbInvest>(TbInvest.class);
        util.exportExcel(response, list, "投资数据");
    }

    /**
     * 获取投资详细信息
     */
//    @RequiresPermissions("system:invest:query")
    @GetMapping(value = "/{investId}")
    public AjaxResult getInfo(@PathVariable("investId") Long investId)
    {
        return success(tbInvestService.selectTbInvestByInvestId(investId));
    }

    /**
     * 新增投资
     */
//    @RequiresPermissions("system:invest:add")
    @Log(title = "投资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbInvest tbInvest)
    {
        return toAjax(tbInvestService.insertTbInvest(tbInvest));
    }

    /**
     * 修改投资
     */
//    @RequiresPermissions("system:invest:edit")
    @Log(title = "投资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbInvest tbInvest)
    {
        return toAjax(tbInvestService.updateTbInvest(tbInvest));
    }

    /**
     * 删除投资
     */
//    @RequiresPermissions("system:invest:remove")
    @Log(title = "投资", businessType = BusinessType.DELETE)
	@DeleteMapping("/{investIds}")
    public AjaxResult remove(@PathVariable Long[] investIds)
    {
        return toAjax(tbInvestService.deleteTbInvestByInvestIds(investIds));
    }
}
