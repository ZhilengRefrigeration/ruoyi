package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.CategoryEntity;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 查询树形结构分类
     * @return list
     */
    List<CategoryEntity> listWithTree();

    /**
     * 批量删除菜单
     * @param asList ids
     */
    void removeMenuByIds(List<Long> asList);

    /**
     * 找到catelogI的完整路径
     * @param catelogId 分类id
     * @return long s
     */
    Long[] finCatelogPath(Long catelogId);

    /**
     * 级联更新
     * @param category 分类实体类
     */
    void updateCascade(CategoryEntity category);
}
