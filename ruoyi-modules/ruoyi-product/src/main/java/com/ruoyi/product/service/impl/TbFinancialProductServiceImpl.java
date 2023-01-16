package com.ruoyi.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.product.pojo.TbFinancialProduct;
import com.ruoyi.product.pojo.vo.IdVo;
import com.ruoyi.product.pojo.vo.PageInfoVo;
import com.ruoyi.product.pojo.vo.ProductInfoVo;
import com.ruoyi.product.service.TbFinancialProductService;
import com.ruoyi.product.mapper.TbFinancialProductMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TbFinancialProductServiceImpl extends ServiceImpl<TbFinancialProductMapper, TbFinancialProduct>
    implements TbFinancialProductService{
    @Autowired
    private TbFinancialProductMapper tbFinancialProductMapper;

    /**
     * 产品数据查询
     * @param pageInfoVo
     * @return
     */
    @Override
    public AjaxResult listPage(PageInfoVo pageInfoVo) {
        Page<TbFinancialProduct> page = new Page<>(pageInfoVo.getPageNum(), pageInfoVo.getPageSize());
        Page<TbFinancialProduct> financialProductPage = page(page, new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getFirmName, pageInfoVo.getKeyword()).last("limit 1"));
        List<ProductInfoVo> collect = financialProductPage.getRecords().stream().map((item) -> {
            ProductInfoVo productInfoVo = new ProductInfoVo();
            BeanUtils.copyProperties(item, productInfoVo);
            return productInfoVo;

        }).collect(Collectors.toList());
        Page<ProductInfoVo> infoVoPage = new Page<>();
        infoVoPage.setRecords(collect);
        infoVoPage.setCurrent(financialProductPage.getCurrent());
        infoVoPage.setSize(financialProductPage.getSize());
        infoVoPage.setTotal(financialProductPage.getTotal());

        return AjaxResult.success(infoVoPage);
    }

    /**
     * 添加列表数据
     * @param productInfoVo
     * @return
     */
    @Override
    public AjaxResult add(ProductInfoVo productInfoVo) {
        String firmName = productInfoVo.getFirmName();
        TbFinancialProduct one = getOne(new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getFirmName, firmName));
        if(one!=null){
            return AjaxResult.error(302,"产品名称重复");
        }
        TbFinancialProduct financialProduct = new TbFinancialProduct();
        BeanUtils.copyProperties(productInfoVo,financialProduct);
        save(financialProduct);


        return AjaxResult.success();
    }

    /**
     * 删除数据通过id
     * @param idVo
     * @return
     */
    @Override
    public AjaxResult deleteById(IdVo idVo) {
        Integer productId = idVo.getProductId();
        TbFinancialProduct one = getOne(new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getProductId, productId));
        if(one==null){
            return AjaxResult.error(402,"产品Id不存在");
        }
        Integer oneProductId = one.getProductId();

        BeanUtils.copyProperties(idVo,one);
        int i = tbFinancialProductMapper.deleteById(oneProductId);
        if(i>0){
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.error(302,"删除失败");
        }

    }

    /**
     * 修改数据通过id
     * @param productInfoVo
     * @return
     */
    @Override
    public AjaxResult updateId(ProductInfoVo productInfoVo) {
        Integer productId = productInfoVo.getProductId();
        TbFinancialProduct one = getOne(new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getProductId, productId));
        if(one==null){
            return AjaxResult.error(402,"产品Id不存在");
        }
        TbFinancialProduct product = new TbFinancialProduct();
        //
        BeanUtils.copyProperties(productInfoVo,product);
        tbFinancialProductMapper.updateById(product);
        return AjaxResult.success("修改成功");
    }
}




