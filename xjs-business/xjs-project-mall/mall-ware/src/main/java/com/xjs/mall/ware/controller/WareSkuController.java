package com.xjs.mall.ware.controller;

import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.mall.ware.service.WareSkuService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.other.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 商品库存
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 09:56:19
 */
@RestController
@RequestMapping("ware/waresku")
@Api(tags = "商城-仓库-商品库存")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id){
		WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody WareSkuEntity wareSku){
		wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody WareSkuEntity wareSku){
		wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Long[] ids){
		wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
