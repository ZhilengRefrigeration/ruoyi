package com.xjs.mall.search.controller;

import com.xjs.mall.search.service.MallSearchService;
import com.xjs.mall.search.vo.SearchParam;
import com.xjs.mall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejs
 * @since 2022-05-11
 */
@Controller
public class SearchController {

    @Autowired
    MallSearchService mallSearchService;

    /**
     * 自动将页面提交过来的所有请求查询参数封装成指定的对象
     *
     * @param param 检索条件
     * @return url
     */
    @GetMapping("/search.html")
    public String listPage(SearchParam param, Model model) {
        //1、根据传递来的页面的查询参数，去es检索商品
        SearchResult result = mallSearchService.search(param);

        model.addAttribute("result", result);

        return "list";
    }

}
