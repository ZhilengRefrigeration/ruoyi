package com.xjs.mall.product.controller;

import com.xjs.mall.product.entity.SkuInfoEntity;
import com.xjs.mall.product.service.SkuInfoService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.other.R;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sku信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/skuinfo")
@Api(tags = "商城-商品-SKU信息")
public class SkuInfoController extends MyBaseController<SkuInfoEntity> {
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        super.checkParams(params);
        PageUtils page = skuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

}
