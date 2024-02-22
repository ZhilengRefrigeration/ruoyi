package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.ISysSequenceService;
import com.ruoyi.common.services.constants.SeqType;
import com.ruoyi.wms.domain.ItemType;
import com.ruoyi.wms.mapper.ItemTypeDynamicSqlSupport;
import com.ruoyi.wms.mapper.ItemTypeMapper;
import com.ruoyi.wms.service.IItemTypeService;
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
 * 物品类型Service业务层处理
 *
 * @author ryas
 * created on 2024-02-20
 */
@Service
public class ItemTypeServiceImpl implements IItemTypeService {
    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @Autowired
    private ISysSequenceService sequenceService;

    /**
     * 查询物品类型
     *
     * @param itemTypeCd 物品类型主键
     * @return 物品类型
     */
    @Override
    public ItemType selectItemTypeByItemTypeCd(String itemTypeCd) {
        Optional<ItemType> result = itemTypeMapper.selectOne(dsl -> dsl.where(ItemTypeDynamicSqlSupport.itemTypeCd, SqlBuilder.isEqualTo(itemTypeCd)));
        return result.orElse(null);
    }

    /**
     * 查询物品类型列表
     *
     * @param itemType 物品类型
     * @return 物品类型
     */
    @Override
    public List<ItemType> selectItemTypeList(ItemType itemType) {
        SelectStatementProvider provider = SqlBuilder.select(ItemTypeMapper.selectList)
                .from(ItemTypeDynamicSqlSupport.itemType)
                .where(ItemTypeDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(ItemTypeDynamicSqlSupport.itemTypeName, SqlBuilder.isLikeWhenPresent(itemType.getItemTypeName() == null ? null : "%" + itemType.getItemTypeName() + "%"))
                .and(ItemTypeDynamicSqlSupport.remark1, SqlBuilder.isEqualToWhenPresent(itemType.getRemark1()))
                .orderBy(ItemTypeDynamicSqlSupport.itemTypeCd)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return itemTypeMapper.selectMany(provider);
    }

    /**
     * 新增物品类型
     *
     * @param itemType 物品类型
     * @return 结果
     */
    @Transactional
    @Override
    public int insertItemType(ItemType itemType) {
        if (StringUtils.isBlank(itemType.getItemTypeCd())) {
            String itemTypeCd = sequenceService.getNextSequence(SeqType.ITEM_TYPE_CD);
            itemType.setItemTypeCd(itemTypeCd);
        }
        return itemTypeMapper.insertSelective(itemType);
    }

    /**
     * 修改物品类型
     *
     * @param itemType 物品类型
     * @return 结果
     */
    @Transactional
    @Override
    public int updateItemType(ItemType itemType) {
        return itemTypeMapper.updateByPrimaryKeySelective(itemType);
    }

    /**
     * 批量删除物品类型
     *
     * @param itemTypeCds 需要删除的物品类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteItemTypeByItemTypeCds(String[] itemTypeCds) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(ItemTypeDynamicSqlSupport.itemType)
                .set(ItemTypeDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(ItemTypeDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(ItemTypeDynamicSqlSupport.updateBy).equalTo(userId)
                .where(ItemTypeDynamicSqlSupport.itemTypeCd, SqlBuilder.isIn(itemTypeCds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return itemTypeMapper.update(provider);
    }

    /**
     * 删除物品类型信息
     *
     * @param itemTypeCd 物品类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteItemTypeByItemTypeCd(String itemTypeCd) {
        ItemType record = new ItemType();
        record.setItemTypeCd(itemTypeCd);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        record.setUpdateTime(DateUtils.getNowDate());
        return itemTypeMapper.updateByPrimaryKey(record);
    }
}
