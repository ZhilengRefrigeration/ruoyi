package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.ItemInfo;
import com.ruoyi.wms.mapper.ItemInfoDynamicSqlSupport;
import com.ruoyi.wms.mapper.ItemInfoMapper;
import com.ruoyi.wms.service.IItemInfoService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 物品基础信息Service业务层处理
 *
 * @author ryas
 * created on 2024-02-20
 */
@Service
public class ItemInfoServiceImpl implements IItemInfoService {
    @Autowired
    private ItemInfoMapper itemInfoMapper;

    /**
     * 查询物品基础信息
     *
     * @param itemCd 物品基础信息主键
     * @return 物品基础信息
     */
    @Override
    public ItemInfo selectItemInfoByItemCd(String itemCd) {
        Optional<ItemInfo> result = itemInfoMapper.selectOne(dsl -> dsl.where(ItemInfoDynamicSqlSupport.itemCd, SqlBuilder.isEqualTo(itemCd)));
        return result.orElse(null);
    }

    /**
     * 查询物品基础信息列表
     *
     * @param itemInfo 物品基础信息
     * @return 物品基础信息
     */
    @Override
    public List<ItemInfo> selectItemInfoList(ItemInfo itemInfo) {
        SelectStatementProvider provider = SqlBuilder.select(ItemInfoMapper.selectList)
                .from(ItemInfoDynamicSqlSupport.itemInfo)
                .where(ItemInfoDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(ItemInfoDynamicSqlSupport.itemCd, SqlBuilder.isEqualToWhenPresent(itemInfo.getItemCd()))
                .and(ItemInfoDynamicSqlSupport.itemName, SqlBuilder.isLikeWhenPresent(itemInfo.getItemName() == null ? null : "%" + itemInfo.getItemName() + "%"))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return itemInfoMapper.selectMany(provider);
    }

    /**
     * 新增物品基础信息
     *
     * @param itemInfo 物品基础信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertItemInfo(ItemInfo itemInfo) {
        return itemInfoMapper.insertSelective(itemInfo);
    }

    /**
     * 修改物品基础信息
     *
     * @param itemInfo 物品基础信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateItemInfo(ItemInfo itemInfo) {
        return itemInfoMapper.updateByPrimaryKeySelective(itemInfo);
    }

    /**
     * 批量删除物品基础信息
     *
     * @param itemCds 需要删除的物品基础信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteItemInfoByItemCds(String[] itemCds) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(ItemInfoDynamicSqlSupport.itemInfo)
                .set(ItemInfoDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(ItemInfoDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(ItemInfoDynamicSqlSupport.updateBy).equalTo(userId)
                .where(ItemInfoDynamicSqlSupport.itemCd, SqlBuilder.isIn(itemCds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return itemInfoMapper.update(provider);
    }

    /**
     * 删除物品基础信息信息
     *
     * @param itemCd 物品基础信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteItemInfoByItemCd(String itemCd) {
        ItemInfo record = new ItemInfo();
        record.setItemCd(itemCd);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        record.setUpdateTime(DateUtils.getNowDate());
        return itemInfoMapper.updateByPrimaryKey(record);
    }
}
