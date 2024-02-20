package com.ruoyi.wms.mapper;

import static com.ruoyi.wms.mapper.ItemInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.ItemInfo;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface ItemInfoMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ItemInfo>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(itemCd, deptId, itemName, supplier, prepLeadTime, instockLeadTime, restingPeriod, outstockLeadTime, spec1, spec2, spec3, spec4, spec5, cls1, cls2, cls3, cls4, cls5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark, remark1, remark2, remark3, remark4, remark5, deliveryPeriod, defaultStgBinCd, enableFlg, safetyStock, maxInvQty, purchLimitQty, goodsCls, lotNoMgmtCls, itemTypeCd, stdUnitCd, pkgUnitCd, outstockReqMinQty, outstockUnitCls, netWeightPerUnit, ownVolM3, sizeD, sizeW, sizeH, pkgRqmtDesc, stackingRqmtDesc, stgRqmtDesc, manufacturer, pictureId, pictureUrl);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ItemInfoResult", value = {
        @Result(column="ITEM_CD", property="itemCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_NAME", property="itemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUPPLIER", property="supplier", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREP_LEAD_TIME", property="prepLeadTime", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSTOCK_LEAD_TIME", property="instockLeadTime", jdbcType=JdbcType.DECIMAL),
        @Result(column="RESTING_PERIOD", property="restingPeriod", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUTSTOCK_LEAD_TIME", property="outstockLeadTime", jdbcType=JdbcType.DECIMAL),
        @Result(column="SPEC_1", property="spec1", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_2", property="spec2", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_3", property="spec3", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_4", property="spec4", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_5", property="spec5", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLS_1", property="cls1", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLS_2", property="cls2", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLS_3", property="cls3", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLS_4", property="cls4", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLS_5", property="cls5", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_COUNT", property="updateCount", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_1", property="remark1", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_2", property="remark2", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_3", property="remark3", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_4", property="remark4", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_5", property="remark5", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVERY_PERIOD", property="deliveryPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEFAULT_STG_BIN_CD", property="defaultStgBinCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLE_FLG", property="enableFlg", jdbcType=JdbcType.INTEGER),
        @Result(column="SAFETY_STOCK", property="safetyStock", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_INV_QTY", property="maxInvQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="PURCH_LIMIT_QTY", property="purchLimitQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="GOODS_CLS", property="goodsCls", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOT_NO_MGMT_CLS", property="lotNoMgmtCls", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_TYPE_CD", property="itemTypeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="STD_UNIT_CD", property="stdUnitCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PKG_UNIT_CD", property="pkgUnitCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUTSTOCK_REQ_MIN_QTY", property="outstockReqMinQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUTSTOCK_UNIT_CLS", property="outstockUnitCls", jdbcType=JdbcType.VARCHAR),
        @Result(column="NET_WEIGHT_PER_UNIT", property="netWeightPerUnit", jdbcType=JdbcType.DECIMAL),
        @Result(column="OWN_VOL_M3", property="ownVolM3", jdbcType=JdbcType.DECIMAL),
        @Result(column="SIZE_D", property="sizeD", jdbcType=JdbcType.DECIMAL),
        @Result(column="SIZE_W", property="sizeW", jdbcType=JdbcType.DECIMAL),
        @Result(column="SIZE_H", property="sizeH", jdbcType=JdbcType.DECIMAL),
        @Result(column="PKG_RQMT_DESC", property="pkgRqmtDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="STACKING_RQMT_DESC", property="stackingRqmtDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="STG_RQMT_DESC", property="stgRqmtDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="MANUFACTURER", property="manufacturer", jdbcType=JdbcType.VARCHAR),
        @Result(column="PICTURE_ID", property="pictureId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PICTURE_URL", property="pictureUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<ItemInfo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ItemInfoResult")
    Optional<ItemInfo> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, itemInfo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, itemInfo, completer);
    }

    default int deleteByPrimaryKey(String itemCd_) {
        return delete(c -> 
            c.where(itemCd, isEqualTo(itemCd_))
        );
    }

    default int insert(ItemInfo row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, itemInfo, c ->
            c.map(itemCd).toProperty("itemCd")
            .map(deptId).toProperty("deptId")
            .map(itemName).toProperty("itemName")
            .map(supplier).toProperty("supplier")
            .map(prepLeadTime).toProperty("prepLeadTime")
            .map(instockLeadTime).toProperty("instockLeadTime")
            .map(restingPeriod).toProperty("restingPeriod")
            .map(outstockLeadTime).toProperty("outstockLeadTime")
            .map(spec1).toProperty("spec1")
            .map(spec2).toProperty("spec2")
            .map(spec3).toProperty("spec3")
            .map(spec4).toProperty("spec4")
            .map(spec5).toProperty("spec5")
            .map(cls1).toProperty("cls1")
            .map(cls2).toProperty("cls2")
            .map(cls3).toProperty("cls3")
            .map(cls4).toProperty("cls4")
            .map(cls5).toProperty("cls5")
            .map(updateCount).toProperty("updateCount")
            .map(deleteFlag).toProperty("deleteFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(deliveryPeriod).toProperty("deliveryPeriod")
            .map(defaultStgBinCd).toProperty("defaultStgBinCd")
            .map(enableFlg).toProperty("enableFlg")
            .map(safetyStock).toProperty("safetyStock")
            .map(maxInvQty).toProperty("maxInvQty")
            .map(purchLimitQty).toProperty("purchLimitQty")
            .map(goodsCls).toProperty("goodsCls")
            .map(lotNoMgmtCls).toProperty("lotNoMgmtCls")
            .map(itemTypeCd).toProperty("itemTypeCd")
            .map(stdUnitCd).toProperty("stdUnitCd")
            .map(pkgUnitCd).toProperty("pkgUnitCd")
            .map(outstockReqMinQty).toProperty("outstockReqMinQty")
            .map(outstockUnitCls).toProperty("outstockUnitCls")
            .map(netWeightPerUnit).toProperty("netWeightPerUnit")
            .map(ownVolM3).toProperty("ownVolM3")
            .map(sizeD).toProperty("sizeD")
            .map(sizeW).toProperty("sizeW")
            .map(sizeH).toProperty("sizeH")
            .map(pkgRqmtDesc).toProperty("pkgRqmtDesc")
            .map(stackingRqmtDesc).toProperty("stackingRqmtDesc")
            .map(stgRqmtDesc).toProperty("stgRqmtDesc")
            .map(manufacturer).toProperty("manufacturer")
            .map(pictureId).toProperty("pictureId")
            .map(pictureUrl).toProperty("pictureUrl")
        );
    }

    default int insertMultiple(Collection<ItemInfo> records) {
        for (ItemInfo row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, itemInfo, c ->
            c.map(itemCd).toProperty("itemCd")
            .map(deptId).toProperty("deptId")
            .map(itemName).toProperty("itemName")
            .map(supplier).toProperty("supplier")
            .map(prepLeadTime).toProperty("prepLeadTime")
            .map(instockLeadTime).toProperty("instockLeadTime")
            .map(restingPeriod).toProperty("restingPeriod")
            .map(outstockLeadTime).toProperty("outstockLeadTime")
            .map(spec1).toProperty("spec1")
            .map(spec2).toProperty("spec2")
            .map(spec3).toProperty("spec3")
            .map(spec4).toProperty("spec4")
            .map(spec5).toProperty("spec5")
            .map(cls1).toProperty("cls1")
            .map(cls2).toProperty("cls2")
            .map(cls3).toProperty("cls3")
            .map(cls4).toProperty("cls4")
            .map(cls5).toProperty("cls5")
            .map(updateCount).toProperty("updateCount")
            .map(deleteFlag).toProperty("deleteFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(deliveryPeriod).toProperty("deliveryPeriod")
            .map(defaultStgBinCd).toProperty("defaultStgBinCd")
            .map(enableFlg).toProperty("enableFlg")
            .map(safetyStock).toProperty("safetyStock")
            .map(maxInvQty).toProperty("maxInvQty")
            .map(purchLimitQty).toProperty("purchLimitQty")
            .map(goodsCls).toProperty("goodsCls")
            .map(lotNoMgmtCls).toProperty("lotNoMgmtCls")
            .map(itemTypeCd).toProperty("itemTypeCd")
            .map(stdUnitCd).toProperty("stdUnitCd")
            .map(pkgUnitCd).toProperty("pkgUnitCd")
            .map(outstockReqMinQty).toProperty("outstockReqMinQty")
            .map(outstockUnitCls).toProperty("outstockUnitCls")
            .map(netWeightPerUnit).toProperty("netWeightPerUnit")
            .map(ownVolM3).toProperty("ownVolM3")
            .map(sizeD).toProperty("sizeD")
            .map(sizeW).toProperty("sizeW")
            .map(sizeH).toProperty("sizeH")
            .map(pkgRqmtDesc).toProperty("pkgRqmtDesc")
            .map(stackingRqmtDesc).toProperty("stackingRqmtDesc")
            .map(stgRqmtDesc).toProperty("stgRqmtDesc")
            .map(manufacturer).toProperty("manufacturer")
            .map(pictureId).toProperty("pictureId")
            .map(pictureUrl).toProperty("pictureUrl")
        );
    }

    default int insertSelective(ItemInfo row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, itemInfo, c ->
            c.map(itemCd).toPropertyWhenPresent("itemCd", row::getItemCd)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(itemName).toPropertyWhenPresent("itemName", row::getItemName)
            .map(supplier).toPropertyWhenPresent("supplier", row::getSupplier)
            .map(prepLeadTime).toPropertyWhenPresent("prepLeadTime", row::getPrepLeadTime)
            .map(instockLeadTime).toPropertyWhenPresent("instockLeadTime", row::getInstockLeadTime)
            .map(restingPeriod).toPropertyWhenPresent("restingPeriod", row::getRestingPeriod)
            .map(outstockLeadTime).toPropertyWhenPresent("outstockLeadTime", row::getOutstockLeadTime)
            .map(spec1).toPropertyWhenPresent("spec1", row::getSpec1)
            .map(spec2).toPropertyWhenPresent("spec2", row::getSpec2)
            .map(spec3).toPropertyWhenPresent("spec3", row::getSpec3)
            .map(spec4).toPropertyWhenPresent("spec4", row::getSpec4)
            .map(spec5).toPropertyWhenPresent("spec5", row::getSpec5)
            .map(cls1).toPropertyWhenPresent("cls1", row::getCls1)
            .map(cls2).toPropertyWhenPresent("cls2", row::getCls2)
            .map(cls3).toPropertyWhenPresent("cls3", row::getCls3)
            .map(cls4).toPropertyWhenPresent("cls4", row::getCls4)
            .map(cls5).toPropertyWhenPresent("cls5", row::getCls5)
            .map(updateCount).toPropertyWhenPresent("updateCount", row::getUpdateCount)
            .map(deleteFlag).toPropertyWhenPresent("deleteFlag", row::getDeleteFlag)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
            .map(remark1).toPropertyWhenPresent("remark1", row::getRemark1)
            .map(remark2).toPropertyWhenPresent("remark2", row::getRemark2)
            .map(remark3).toPropertyWhenPresent("remark3", row::getRemark3)
            .map(remark4).toPropertyWhenPresent("remark4", row::getRemark4)
            .map(remark5).toPropertyWhenPresent("remark5", row::getRemark5)
            .map(deliveryPeriod).toPropertyWhenPresent("deliveryPeriod", row::getDeliveryPeriod)
            .map(defaultStgBinCd).toPropertyWhenPresent("defaultStgBinCd", row::getDefaultStgBinCd)
            .map(enableFlg).toPropertyWhenPresent("enableFlg", row::getEnableFlg)
            .map(safetyStock).toPropertyWhenPresent("safetyStock", row::getSafetyStock)
            .map(maxInvQty).toPropertyWhenPresent("maxInvQty", row::getMaxInvQty)
            .map(purchLimitQty).toPropertyWhenPresent("purchLimitQty", row::getPurchLimitQty)
            .map(goodsCls).toPropertyWhenPresent("goodsCls", row::getGoodsCls)
            .map(lotNoMgmtCls).toPropertyWhenPresent("lotNoMgmtCls", row::getLotNoMgmtCls)
            .map(itemTypeCd).toPropertyWhenPresent("itemTypeCd", row::getItemTypeCd)
            .map(stdUnitCd).toPropertyWhenPresent("stdUnitCd", row::getStdUnitCd)
            .map(pkgUnitCd).toPropertyWhenPresent("pkgUnitCd", row::getPkgUnitCd)
            .map(outstockReqMinQty).toPropertyWhenPresent("outstockReqMinQty", row::getOutstockReqMinQty)
            .map(outstockUnitCls).toPropertyWhenPresent("outstockUnitCls", row::getOutstockUnitCls)
            .map(netWeightPerUnit).toPropertyWhenPresent("netWeightPerUnit", row::getNetWeightPerUnit)
            .map(ownVolM3).toPropertyWhenPresent("ownVolM3", row::getOwnVolM3)
            .map(sizeD).toPropertyWhenPresent("sizeD", row::getSizeD)
            .map(sizeW).toPropertyWhenPresent("sizeW", row::getSizeW)
            .map(sizeH).toPropertyWhenPresent("sizeH", row::getSizeH)
            .map(pkgRqmtDesc).toPropertyWhenPresent("pkgRqmtDesc", row::getPkgRqmtDesc)
            .map(stackingRqmtDesc).toPropertyWhenPresent("stackingRqmtDesc", row::getStackingRqmtDesc)
            .map(stgRqmtDesc).toPropertyWhenPresent("stgRqmtDesc", row::getStgRqmtDesc)
            .map(manufacturer).toPropertyWhenPresent("manufacturer", row::getManufacturer)
            .map(pictureId).toPropertyWhenPresent("pictureId", row::getPictureId)
            .map(pictureUrl).toPropertyWhenPresent("pictureUrl", row::getPictureUrl)
        );
    }

    default Optional<ItemInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, itemInfo, completer);
    }

    default List<ItemInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, itemInfo, completer);
    }

    default List<ItemInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, itemInfo, completer);
    }

    default Optional<ItemInfo> selectByPrimaryKey(String itemCd_) {
        return selectOne(c ->
            c.where(itemCd, isEqualTo(itemCd_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, itemInfo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(ItemInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(itemCd).equalTo(row::getItemCd)
                .set(deptId).equalTo(row::getDeptId)
                .set(itemName).equalTo(row::getItemName)
                .set(supplier).equalTo(row::getSupplier)
                .set(prepLeadTime).equalTo(row::getPrepLeadTime)
                .set(instockLeadTime).equalTo(row::getInstockLeadTime)
                .set(restingPeriod).equalTo(row::getRestingPeriod)
                .set(outstockLeadTime).equalTo(row::getOutstockLeadTime)
                .set(spec1).equalTo(row::getSpec1)
                .set(spec2).equalTo(row::getSpec2)
                .set(spec3).equalTo(row::getSpec3)
                .set(spec4).equalTo(row::getSpec4)
                .set(spec5).equalTo(row::getSpec5)
                .set(cls1).equalTo(row::getCls1)
                .set(cls2).equalTo(row::getCls2)
                .set(cls3).equalTo(row::getCls3)
                .set(cls4).equalTo(row::getCls4)
                .set(cls5).equalTo(row::getCls5)
                .set(updateCount).equalTo(row::getUpdateCount)
                .set(deleteFlag).equalTo(row::getDeleteFlag)
                .set(createBy).equalTo(row::getCreateBy)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateBy).equalTo(row::getUpdateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(remark).equalTo(row::getRemark)
                .set(remark1).equalTo(row::getRemark1)
                .set(remark2).equalTo(row::getRemark2)
                .set(remark3).equalTo(row::getRemark3)
                .set(remark4).equalTo(row::getRemark4)
                .set(remark5).equalTo(row::getRemark5)
                .set(deliveryPeriod).equalTo(row::getDeliveryPeriod)
                .set(defaultStgBinCd).equalTo(row::getDefaultStgBinCd)
                .set(enableFlg).equalTo(row::getEnableFlg)
                .set(safetyStock).equalTo(row::getSafetyStock)
                .set(maxInvQty).equalTo(row::getMaxInvQty)
                .set(purchLimitQty).equalTo(row::getPurchLimitQty)
                .set(goodsCls).equalTo(row::getGoodsCls)
                .set(lotNoMgmtCls).equalTo(row::getLotNoMgmtCls)
                .set(itemTypeCd).equalTo(row::getItemTypeCd)
                .set(stdUnitCd).equalTo(row::getStdUnitCd)
                .set(pkgUnitCd).equalTo(row::getPkgUnitCd)
                .set(outstockReqMinQty).equalTo(row::getOutstockReqMinQty)
                .set(outstockUnitCls).equalTo(row::getOutstockUnitCls)
                .set(netWeightPerUnit).equalTo(row::getNetWeightPerUnit)
                .set(ownVolM3).equalTo(row::getOwnVolM3)
                .set(sizeD).equalTo(row::getSizeD)
                .set(sizeW).equalTo(row::getSizeW)
                .set(sizeH).equalTo(row::getSizeH)
                .set(pkgRqmtDesc).equalTo(row::getPkgRqmtDesc)
                .set(stackingRqmtDesc).equalTo(row::getStackingRqmtDesc)
                .set(stgRqmtDesc).equalTo(row::getStgRqmtDesc)
                .set(manufacturer).equalTo(row::getManufacturer)
                .set(pictureId).equalTo(row::getPictureId)
                .set(pictureUrl).equalTo(row::getPictureUrl);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(ItemInfo row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(itemCd).equalToWhenPresent(row::getItemCd)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(itemName).equalToWhenPresent(row::getItemName)
                .set(supplier).equalToWhenPresent(row::getSupplier)
                .set(prepLeadTime).equalToWhenPresent(row::getPrepLeadTime)
                .set(instockLeadTime).equalToWhenPresent(row::getInstockLeadTime)
                .set(restingPeriod).equalToWhenPresent(row::getRestingPeriod)
                .set(outstockLeadTime).equalToWhenPresent(row::getOutstockLeadTime)
                .set(spec1).equalToWhenPresent(row::getSpec1)
                .set(spec2).equalToWhenPresent(row::getSpec2)
                .set(spec3).equalToWhenPresent(row::getSpec3)
                .set(spec4).equalToWhenPresent(row::getSpec4)
                .set(spec5).equalToWhenPresent(row::getSpec5)
                .set(cls1).equalToWhenPresent(row::getCls1)
                .set(cls2).equalToWhenPresent(row::getCls2)
                .set(cls3).equalToWhenPresent(row::getCls3)
                .set(cls4).equalToWhenPresent(row::getCls4)
                .set(cls5).equalToWhenPresent(row::getCls5)
                .set(updateCount).equalToWhenPresent(row::getUpdateCount)
                .set(deleteFlag).equalToWhenPresent(row::getDeleteFlag)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(remark).equalToWhenPresent(row::getRemark)
                .set(remark1).equalToWhenPresent(row::getRemark1)
                .set(remark2).equalToWhenPresent(row::getRemark2)
                .set(remark3).equalToWhenPresent(row::getRemark3)
                .set(remark4).equalToWhenPresent(row::getRemark4)
                .set(remark5).equalToWhenPresent(row::getRemark5)
                .set(deliveryPeriod).equalToWhenPresent(row::getDeliveryPeriod)
                .set(defaultStgBinCd).equalToWhenPresent(row::getDefaultStgBinCd)
                .set(enableFlg).equalToWhenPresent(row::getEnableFlg)
                .set(safetyStock).equalToWhenPresent(row::getSafetyStock)
                .set(maxInvQty).equalToWhenPresent(row::getMaxInvQty)
                .set(purchLimitQty).equalToWhenPresent(row::getPurchLimitQty)
                .set(goodsCls).equalToWhenPresent(row::getGoodsCls)
                .set(lotNoMgmtCls).equalToWhenPresent(row::getLotNoMgmtCls)
                .set(itemTypeCd).equalToWhenPresent(row::getItemTypeCd)
                .set(stdUnitCd).equalToWhenPresent(row::getStdUnitCd)
                .set(pkgUnitCd).equalToWhenPresent(row::getPkgUnitCd)
                .set(outstockReqMinQty).equalToWhenPresent(row::getOutstockReqMinQty)
                .set(outstockUnitCls).equalToWhenPresent(row::getOutstockUnitCls)
                .set(netWeightPerUnit).equalToWhenPresent(row::getNetWeightPerUnit)
                .set(ownVolM3).equalToWhenPresent(row::getOwnVolM3)
                .set(sizeD).equalToWhenPresent(row::getSizeD)
                .set(sizeW).equalToWhenPresent(row::getSizeW)
                .set(sizeH).equalToWhenPresent(row::getSizeH)
                .set(pkgRqmtDesc).equalToWhenPresent(row::getPkgRqmtDesc)
                .set(stackingRqmtDesc).equalToWhenPresent(row::getStackingRqmtDesc)
                .set(stgRqmtDesc).equalToWhenPresent(row::getStgRqmtDesc)
                .set(manufacturer).equalToWhenPresent(row::getManufacturer)
                .set(pictureId).equalToWhenPresent(row::getPictureId)
                .set(pictureUrl).equalToWhenPresent(row::getPictureUrl);
    }

    default int updateByPrimaryKey(ItemInfo row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(itemName).equalTo(row::getItemName)
            .set(supplier).equalTo(row::getSupplier)
            .set(prepLeadTime).equalTo(row::getPrepLeadTime)
            .set(instockLeadTime).equalTo(row::getInstockLeadTime)
            .set(restingPeriod).equalTo(row::getRestingPeriod)
            .set(outstockLeadTime).equalTo(row::getOutstockLeadTime)
            .set(spec1).equalTo(row::getSpec1)
            .set(spec2).equalTo(row::getSpec2)
            .set(spec3).equalTo(row::getSpec3)
            .set(spec4).equalTo(row::getSpec4)
            .set(spec5).equalTo(row::getSpec5)
            .set(cls1).equalTo(row::getCls1)
            .set(cls2).equalTo(row::getCls2)
            .set(cls3).equalTo(row::getCls3)
            .set(cls4).equalTo(row::getCls4)
            .set(cls5).equalTo(row::getCls5)
            .set(updateCount).equalTo(row::getUpdateCount)
            .set(deleteFlag).equalTo(row::getDeleteFlag)
            .set(createBy).equalTo(row::getCreateBy)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(remark).equalTo(row::getRemark)
            .set(remark1).equalTo(row::getRemark1)
            .set(remark2).equalTo(row::getRemark2)
            .set(remark3).equalTo(row::getRemark3)
            .set(remark4).equalTo(row::getRemark4)
            .set(remark5).equalTo(row::getRemark5)
            .set(deliveryPeriod).equalTo(row::getDeliveryPeriod)
            .set(defaultStgBinCd).equalTo(row::getDefaultStgBinCd)
            .set(enableFlg).equalTo(row::getEnableFlg)
            .set(safetyStock).equalTo(row::getSafetyStock)
            .set(maxInvQty).equalTo(row::getMaxInvQty)
            .set(purchLimitQty).equalTo(row::getPurchLimitQty)
            .set(goodsCls).equalTo(row::getGoodsCls)
            .set(lotNoMgmtCls).equalTo(row::getLotNoMgmtCls)
            .set(itemTypeCd).equalTo(row::getItemTypeCd)
            .set(stdUnitCd).equalTo(row::getStdUnitCd)
            .set(pkgUnitCd).equalTo(row::getPkgUnitCd)
            .set(outstockReqMinQty).equalTo(row::getOutstockReqMinQty)
            .set(outstockUnitCls).equalTo(row::getOutstockUnitCls)
            .set(netWeightPerUnit).equalTo(row::getNetWeightPerUnit)
            .set(ownVolM3).equalTo(row::getOwnVolM3)
            .set(sizeD).equalTo(row::getSizeD)
            .set(sizeW).equalTo(row::getSizeW)
            .set(sizeH).equalTo(row::getSizeH)
            .set(pkgRqmtDesc).equalTo(row::getPkgRqmtDesc)
            .set(stackingRqmtDesc).equalTo(row::getStackingRqmtDesc)
            .set(stgRqmtDesc).equalTo(row::getStgRqmtDesc)
            .set(manufacturer).equalTo(row::getManufacturer)
            .set(pictureId).equalTo(row::getPictureId)
            .set(pictureUrl).equalTo(row::getPictureUrl)
            .where(itemCd, isEqualTo(row::getItemCd))
        );
    }

    default int updateByPrimaryKeySelective(ItemInfo row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(itemName).equalToWhenPresent(row::getItemName)
            .set(supplier).equalToWhenPresent(row::getSupplier)
            .set(prepLeadTime).equalToWhenPresent(row::getPrepLeadTime)
            .set(instockLeadTime).equalToWhenPresent(row::getInstockLeadTime)
            .set(restingPeriod).equalToWhenPresent(row::getRestingPeriod)
            .set(outstockLeadTime).equalToWhenPresent(row::getOutstockLeadTime)
            .set(spec1).equalToWhenPresent(row::getSpec1)
            .set(spec2).equalToWhenPresent(row::getSpec2)
            .set(spec3).equalToWhenPresent(row::getSpec3)
            .set(spec4).equalToWhenPresent(row::getSpec4)
            .set(spec5).equalToWhenPresent(row::getSpec5)
            .set(cls1).equalToWhenPresent(row::getCls1)
            .set(cls2).equalToWhenPresent(row::getCls2)
            .set(cls3).equalToWhenPresent(row::getCls3)
            .set(cls4).equalToWhenPresent(row::getCls4)
            .set(cls5).equalToWhenPresent(row::getCls5)
            .set(updateCount).equalToWhenPresent(row::getUpdateCount)
            .set(deleteFlag).equalToWhenPresent(row::getDeleteFlag)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .set(remark1).equalToWhenPresent(row::getRemark1)
            .set(remark2).equalToWhenPresent(row::getRemark2)
            .set(remark3).equalToWhenPresent(row::getRemark3)
            .set(remark4).equalToWhenPresent(row::getRemark4)
            .set(remark5).equalToWhenPresent(row::getRemark5)
            .set(deliveryPeriod).equalToWhenPresent(row::getDeliveryPeriod)
            .set(defaultStgBinCd).equalToWhenPresent(row::getDefaultStgBinCd)
            .set(enableFlg).equalToWhenPresent(row::getEnableFlg)
            .set(safetyStock).equalToWhenPresent(row::getSafetyStock)
            .set(maxInvQty).equalToWhenPresent(row::getMaxInvQty)
            .set(purchLimitQty).equalToWhenPresent(row::getPurchLimitQty)
            .set(goodsCls).equalToWhenPresent(row::getGoodsCls)
            .set(lotNoMgmtCls).equalToWhenPresent(row::getLotNoMgmtCls)
            .set(itemTypeCd).equalToWhenPresent(row::getItemTypeCd)
            .set(stdUnitCd).equalToWhenPresent(row::getStdUnitCd)
            .set(pkgUnitCd).equalToWhenPresent(row::getPkgUnitCd)
            .set(outstockReqMinQty).equalToWhenPresent(row::getOutstockReqMinQty)
            .set(outstockUnitCls).equalToWhenPresent(row::getOutstockUnitCls)
            .set(netWeightPerUnit).equalToWhenPresent(row::getNetWeightPerUnit)
            .set(ownVolM3).equalToWhenPresent(row::getOwnVolM3)
            .set(sizeD).equalToWhenPresent(row::getSizeD)
            .set(sizeW).equalToWhenPresent(row::getSizeW)
            .set(sizeH).equalToWhenPresent(row::getSizeH)
            .set(pkgRqmtDesc).equalToWhenPresent(row::getPkgRqmtDesc)
            .set(stackingRqmtDesc).equalToWhenPresent(row::getStackingRqmtDesc)
            .set(stgRqmtDesc).equalToWhenPresent(row::getStgRqmtDesc)
            .set(manufacturer).equalToWhenPresent(row::getManufacturer)
            .set(pictureId).equalToWhenPresent(row::getPictureId)
            .set(pictureUrl).equalToWhenPresent(row::getPictureUrl)
            .where(itemCd, isEqualTo(row::getItemCd))
        );
    }
}