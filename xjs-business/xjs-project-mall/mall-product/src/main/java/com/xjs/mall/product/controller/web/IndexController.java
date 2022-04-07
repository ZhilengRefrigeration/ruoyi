package com.xjs.mall.product.controller.web;

import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.Catelog2Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 *
 * @author xiejs
 * @since 2022-04-07
 */
@Controller
@Api(tags = "商城-商品-首页")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
        //查出所有一级分类
        List<CategoryEntity> categoryEntityList=categoryService.getLevel1Categorys();
        model.addAttribute("categorys", categoryEntityList);
        return "index";
    }

    @GetMapping("/index/json/catalog.json")
    @ResponseBody
    @ApiOperation("获取三级分类")
    public Map<String,List<Catelog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }




}

