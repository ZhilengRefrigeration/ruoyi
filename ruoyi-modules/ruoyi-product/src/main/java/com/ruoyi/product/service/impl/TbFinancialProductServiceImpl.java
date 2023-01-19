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

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 产品数据查询
     * @param pageInfoVo
     * @return
     */
    @Override
    public AjaxResult listPage(PageInfoVo pageInfoVo) {
        if(pageInfoVo.getKeyword()==null){
            Page<TbFinancialProduct> page = new Page<>(pageInfoVo.getPageNum(), pageInfoVo.getPageSize());
            //.lambda().eq(TbFinancialProduct::getFirmName, pageInfoVo.getKeyword()).last("limit 1")
            Page<TbFinancialProduct> financialProductPage = page(page, new QueryWrapper<>());
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
        //获取分页数据
        Pageable page = PageRequest.of(pageInfoVo.getPageNum() - 1, pageInfoVo.getPageSize());
        TermQueryBuilder termQuery = QueryBuilders.termQuery("productName", pageInfoVo.getKeyword());
        SortBuilder sortBuilder = new FieldSortBuilder("createTime").order(SortOrder.DESC);
        //高亮查询数据
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<strong style='color:red'>")
                .postTags("</strong>")
                .field("productName");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQuery)
                .withPageable(page)
                .withSort(sortBuilder)
                .withHighlightBuilder(highlightBuilder)
//                .withQuery(queryBuilder)
                .build();
        long total = elasticsearchRestTemplate.count(searchQuery, TbFinancialProduct.class);
        SearchHits<TbFinancialProduct> search = elasticsearchRestTemplate.search(searchQuery, TbFinancialProduct.class);
        List<ProductInfoVo> productInfoVoList = search.getSearchHits().stream().map((item) -> {
            TbFinancialProduct content = item.getContent();
            List<String> productName = item.getHighlightField("productName");
            if (productName != null && productName.size() > 0) {
                String name = productName.get(0);
                //设置高亮查询
                content.setProductName(name);

            }
            ProductInfoVo productInfoVo = new ProductInfoVo();
            BeanUtils.copyProperties(content, productInfoVo);
            return productInfoVo;
        }).collect(Collectors.toList());
        Page<ProductInfoVo> infoVoPage = new Page<>();
        infoVoPage.setRecords(productInfoVoList);
        infoVoPage.setCurrent(pageInfoVo.getPageNum()-1);
        infoVoPage.setSize(pageInfoVo.getPageSize());
        infoVoPage.setTotal(total);

        return AjaxResult.success(infoVoPage);


    }

    /**
     * 添加列表数据
     * @param productInfoVo
     * @return
     */
    @Override
    @Transactional //分布式事务
    public AjaxResult add(ProductInfoVo productInfoVo) {
        String firmName = productInfoVo.getFirmName();
        TbFinancialProduct one = getOne(new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getFirmName, firmName));
        if(one!=null){
            return AjaxResult.error(302,"产品名称重复");
        }
        TbFinancialProduct financialProduct = new TbFinancialProduct();
        BeanUtils.copyProperties(productInfoVo,financialProduct);
        save(financialProduct);
        elasticsearchRestTemplate.save(financialProduct);
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

    @Override
    public AjaxResult selectProduct(ProductInfoVo productInfoVo) {
        Integer productId = productInfoVo.getProductId();
        TbFinancialProduct one = getOne(new QueryWrapper<TbFinancialProduct>().lambda().eq(TbFinancialProduct::getProductId, productId));
        if(one==null){
            return AjaxResult.error(402,"产品Id不存在");
        }
        TbFinancialProduct financialProduct = tbFinancialProductMapper.selectById(productId);
        return AjaxResult.success(financialProduct);
    }
}




