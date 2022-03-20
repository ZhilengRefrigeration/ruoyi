package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.vo.spu.BaseAttrs;
import com.xjs.utils.PageUtils;
import com.xjs.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu的规格参数
     * @param baseAttrs 规格参数数据
     * @param spuId spuId
     */
    void saveProductAttr(List<BaseAttrs> baseAttrs,Long spuId);
}

