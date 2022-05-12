package com.xjs.mall.product.service.impl;

import com.xjs.mall.product.MallProductApp;
import com.xjs.mall.product.service.SkuSaleAttrValueService;
import com.xjs.mall.product.vo.sku.SkuItemSaleAttrVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-05-12
 */
@SpringBootTest(classes = MallProductApp.class)
class SkuSaleAttrValueServiceImplTest {

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Test
    void getSaleAttrsBySpuId() {
        List<SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueService.getSaleAttrsBySpuId(1505566927104974849L);
        System.out.println(saleAttrsBySpuId);
    }
}
