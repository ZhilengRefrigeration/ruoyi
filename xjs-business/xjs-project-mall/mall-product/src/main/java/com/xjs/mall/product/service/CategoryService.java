package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

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
}

