package com.xjs.mall.product.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 详情页面控制器
 * @author xiejs
 * @since 2022-05-12
 */
@Controller
public class ItemController {

    /**
     * 展示当前Sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId) {

        System.out.println(skuId);


        return "item";
    }


}
