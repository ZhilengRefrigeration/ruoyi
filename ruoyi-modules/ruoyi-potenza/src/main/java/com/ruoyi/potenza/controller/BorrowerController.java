package com.ruoyi.potenza.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.potenza.domain.TbBorrower;
import com.ruoyi.potenza.domain.vo.IdVo;
import com.ruoyi.potenza.service.TbBorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.common.core.utils.poi.ExcelUtil;


/**
 * @author 木子
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 16:00
 */
@RequestMapping("/Borrower")
@RestController
@Slf4j
public class BorrowerController extends BaseController {

    @Autowired
    private TbBorrowerService tbBorrowerService;

    @PostMapping("/pageList")
    public TableDataInfo pageList(@RequestBody TbBorrower tbBorrower){
        startPage();
        return getDataTable(tbBorrowerService.pageList(tbBorrower));
    }

    //贷款申请
    @PostMapping("/save")
    public AjaxResult save(@RequestBody TbBorrower tbBorrower){
        boolean save = tbBorrowerService.save(tbBorrower);
        return AjaxResult.success(save);
    }

    //逻辑删除
    @PostMapping("/BorrowerDele")
    public AjaxResult save(@RequestBody IdVo  idVo){
        return tbBorrowerService.borrowerDele(idVo);
    }

    @RequiresPermissions("potenza:borrower:list")
    @GetMapping("/list")
    public TableDataInfo list(TbBorrower tbBorrower)
    {
        startPage();
        List<TbBorrower> list = tbBorrowerService.selectTbBorrowerList(tbBorrower);
        return getDataTable(list);
    }

    /**
     * 导出贷款列表
     */
    @RequiresPermissions("potenza:borrower:export")
    @Log(title = "贷款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbBorrower tbBorrower)
    {
        List<TbBorrower> list = tbBorrowerService.selectTbBorrowerList(tbBorrower);
        ExcelUtil<TbBorrower> util = new ExcelUtil<TbBorrower>(TbBorrower.class);
        util.exportExcel(response, list, "贷款数据");
    }

    /**
     * 获取贷款详细信息
     */
    @RequiresPermissions("potenza:borrower:query")
    @GetMapping(value = "/{borrowerId}")
    public AjaxResult getInfo(@PathVariable("borrowerId") Long borrowerId)
    {
        return success(tbBorrowerService.selectTbBorrowerByBorrowerId(borrowerId));
    }

    /**
     * 新增贷款
     */
    @RequiresPermissions("potenza:borrower:add")
    @Log(title = "贷款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbBorrower tbBorrower)
    {
        return toAjax(tbBorrowerService.insertTbBorrower(tbBorrower));
    }

    /**
     * 修改贷款
     */
    @RequiresPermissions("potenza:borrower:edit")
    @Log(title = "贷款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbBorrower tbBorrower)
    {
        return toAjax(tbBorrowerService.updateTbBorrower(tbBorrower));
    }



}
