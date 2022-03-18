package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.AttrAttrgroupRelationEntity;
import com.xjs.mall.product.vo.AttrGroupRelationVo;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    /**
     * 批量保存属性和属性分组关联信息
     * @param vos ids
     */
    void saveBatch(List<AttrGroupRelationVo> vos);
}

