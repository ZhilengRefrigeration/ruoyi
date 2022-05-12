package com.xjs.mall.product.service.impl;

import com.xjs.mall.product.MallProductApp;
import com.xjs.mall.product.service.AttrGroupService;
import com.xjs.mall.product.vo.sku.SpuItemAttrGroupVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xiejs
 * @since 2022-05-12
 */
@SpringBootTest(classes = MallProductApp.class)
class AttrGroupServiceImplTest {

    @Autowired
    private AttrGroupService attrGroupService;

    @Test
    void getAttrGroupWithAttrsBySpuId() {
        List<SpuItemAttrGroupVo> group = attrGroupService.getAttrGroupWithAttrsBySpuId(1505547655116955650L, 225L);

        System.out.println(group);

    }
}
