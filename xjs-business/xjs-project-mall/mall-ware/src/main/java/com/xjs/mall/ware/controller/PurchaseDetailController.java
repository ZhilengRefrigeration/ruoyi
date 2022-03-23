package com.xjs.mall.ware.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.ware.entity.PurchaseDetailEntity;
import com.xjs.mall.ware.service.PurchaseDetailService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.other.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 采购需求
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 09:56:19
 */
@RestController
@RequestMapping("ware/purchasedetail")
@Api(tags = "商城-仓库-采购需求")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id) {
        PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);

        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ApiOperation("保存")
    @Log(title = "采购需求", businessType = BusinessType.INSERT)
    public R save(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ApiOperation("修改")
    @Log(title = "采购需求", businessType = BusinessType.UPDATE)
    public R update(@RequestBody PurchaseDetailEntity purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "采购需求", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] ids) {
        purchaseDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
