package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.vo.AttrResponseVo;
import com.xjs.mall.product.vo.AttrVo;
import com.xjs.utils.PageUtils;
import com.xjs.mall.product.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
public interface AttrService extends IService<AttrEntity> {

    /**
     * 保存
     * @param attr 商品属性vo
     */
    void saveAttr(AttrVo attr);

    /**
     * 查询基本的规格参数分页
     * @param params 条件
     * @param catelogId 分类id
     * @return pageUtils
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId);

    /**
     * 获取attr规格参数具体信息
     * @param attrId attrId
     * @return vo
     */
    AttrResponseVo getAttrInfo(Long attrId);

    /**
     * 修改规格参数信息
     * @param attr 实体类
     */
    void updateAttr(AttrVo attr);

    /**
     * 删除规格参数及关联信息
     * @param asList ids
     */
    void removeAttr(List<Long> asList);
}

