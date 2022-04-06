package com.xjs.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.mall.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
