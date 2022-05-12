package com.xjs.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.vo.sku.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    /**
     * 通过 Spu Id 获取具有 Attrs 的 Attr 组
     *
     * @param spuId spuId
     * @return SpuItemAttrGroupVo
     */
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
