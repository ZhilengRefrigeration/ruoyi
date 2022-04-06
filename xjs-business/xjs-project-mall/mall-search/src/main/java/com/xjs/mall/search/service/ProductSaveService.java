package com.xjs.mall.search.service;

import com.xjs.mall.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * 商品保存接口
 *
 * @author xiejs
 * @since 2022-04-06
 */
public interface ProductSaveService {

    /**
     * 上架商品
     * @param skuEsModelList 商品es模型
     */
    boolean productStatusUp(List<SkuEsModel> skuEsModelList) throws IOException;
}
