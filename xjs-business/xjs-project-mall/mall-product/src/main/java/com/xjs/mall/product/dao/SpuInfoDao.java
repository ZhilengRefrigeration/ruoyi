package com.xjs.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.mall.product.entity.SpuInfoEntity;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    /**
     * 修改spu状态
     * @param spuId id
     * @param code 状态
     */
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
