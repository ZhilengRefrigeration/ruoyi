package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.BrandEntity;
import com.xjs.mall.product.entity.CategoryBrandRelationEntity;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {


    /**
     * 保存详情
     * @param categoryBrandRelation entity
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新
     * @param brandId 品牌id
     * @param name 品牌名称
     */
    void updateBrand(Long brandId, String name);

    /**
     * 更新
     * @param catId 分类id
     * @param name  分类名称
     */
    void updateCategory(Long catId, String name);

    /**
     * 查询指定分类的品牌信息
     * @param catId 分类id
     * @return list
     */
    List<BrandEntity> getBrandsByCatId(Long catId);
}

