package com.xjs.mall.product.controller.web;

import com.xjs.mall.product.service.SkuInfoService;
import com.xjs.mall.product.vo.sku.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 详情页面控制器
 *
 * @author xiejs
 * @since 2022-05-12
 */
@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 展示当前Sku的详情
     *
     * @param skuId 商品id
     * @return 跳转
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId, Model model) {

        SkuItemVo vo = skuInfoService.item(skuId);

        model.addAttribute("item", vo);

        return "item";
    }


}
