package com.xjs.mall.ware.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.other.R;
import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.mall.ware.service.WareSkuService;
import com.xjs.mall.ware.vo.SkuHasStockVo;
import com.xjs.utils.PageUtils;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品库存
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 09:56:19
 */
@RestController
@RequestMapping("ware/waresku")
@Api(tags = "商城-仓库-商品库存")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;


    //查询sku是否有库存
    @PostMapping("/hasStock")
    public List<SkuHasStockVo> getSkuHasStock(@RequestBody List<Long> skuIds) {

        return wareSkuService.getSkuHasStock(skuIds);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "商品库存", businessType = BusinessType.INSERT)
    public R save(@Validated(AddGroup.class) @RequestBody WareSkuEntity wareSku) {
        wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "商品库存", businessType = BusinessType.UPDATE)
    public R update(@Validated(UpdateGroup.class) @RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "商品库存", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
