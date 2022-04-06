package com.xjs.mall.search.controller;

import com.xjs.exception.BizCodeEnume;
import com.xjs.mall.other.R;
import com.xjs.mall.search.service.ProductSaveService;
import com.xjs.mall.to.es.SkuEsModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * es保存控制器
 *
 * @author xiejs
 * @since 2022-04-06
 */
@RequestMapping("/search")
@RestController
@Api(tags = "商城-检索-ES保存")
@Log4j2
public class ElasticSaveController {

    @Autowired
    private ProductSaveService productSaveService;

    @PostMapping("/product")
    @ApiOperation("上架商品")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModelList) {
        boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModelList);
        } catch (IOException e) {
            log.error("ES商品上架错误:" + e);
            return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }

        if (!b) {
            return R.ok();
        } else {
            return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }

}
