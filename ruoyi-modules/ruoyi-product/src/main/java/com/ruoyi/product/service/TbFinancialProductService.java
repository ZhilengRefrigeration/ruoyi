package com.ruoyi.product.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.product.pojo.TbFinancialProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.product.pojo.vo.IdVo;
import com.ruoyi.product.pojo.vo.PageInfoVo;
import com.ruoyi.product.pojo.vo.ProductInfoVo;



/**
 *
 */
public interface TbFinancialProductService extends IService<TbFinancialProduct> {

    AjaxResult listPage(PageInfoVo pageInfoVo);

    AjaxResult add(ProductInfoVo productInfoVo);

    AjaxResult deleteById(IdVo idVo);

    AjaxResult updateId(ProductInfoVo productInfoVo);
}
