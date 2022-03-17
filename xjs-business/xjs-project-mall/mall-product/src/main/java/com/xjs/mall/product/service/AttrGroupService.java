package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 分页
     *
     * @param params     条件
     * @param categoryId 类别id
     * @return pageUtils
     */
    PageUtils queryPage(Map<String, Object> params, Long categoryId);

    /**
     * 删除属性分组，校验，被引用则不删除
     *
     * @param ids id
     */
    void removeAttrGroup(List<Long> ids);

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     * @param categoryId 分类id
     * @return list
     */
    //List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long categoryId);
}

