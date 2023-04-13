package com.ruoyi.product.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;

import com.ruoyi.product.pojo.vo.IdVo;
import com.ruoyi.product.pojo.vo.PageInfoVo;
import com.ruoyi.product.service.TbFinancialProductService;
import com.ruoyi.product.pojo.vo.ProductInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description: TODO
 * @author杨宗理
 * @date 2023/1/15 16:34
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private TbFinancialProductService tbFinancialProductService;

    /**
     * 展示产品列表数据
     * @param pageInfoVo
     * @return
     */
    @PostMapping(value = "/list")
    public AjaxResult list(@RequestBody PageInfoVo pageInfoVo){
    return tbFinancialProductService.listPage(pageInfoVo);

    }

    /**
     * 添加列表数据
     * @param productInfoVo
     * @return
     */
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestBody ProductInfoVo productInfoVo){
        return tbFinancialProductService.add(productInfoVo);
    }

    /**
     * 删除数据
     * @param idVo
     * @return
     */
    @PostMapping(value = "/delete")
    public AjaxResult delete(@RequestBody IdVo idVo){
        return tbFinancialProductService.deleteById(idVo);
    }

    /**
     *修改数据通过Id
     * @param productInfoVo
     * @return
     */
    @PostMapping(value = "/update")
    public AjaxResult update(@RequestBody ProductInfoVo productInfoVo){
      return tbFinancialProductService.updateId(productInfoVo);


    }

    /**
     * 通过id查询数据
     * @param productInfoVo
     * @return
     */
    @PostMapping(value = "/selectProduct")
    public AjaxResult selectProduct(@RequestBody ProductInfoVo productInfoVo){
        return tbFinancialProductService.selectProduct(productInfoVo);
    }


}
