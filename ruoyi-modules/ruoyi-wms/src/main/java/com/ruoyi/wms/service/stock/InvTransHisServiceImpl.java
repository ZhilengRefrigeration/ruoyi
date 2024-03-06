package com.ruoyi.wms.service.stock;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.InvTransHis;
import com.ruoyi.wms.domain.vo.StockVo;
import com.ruoyi.wms.mapper.stock.InvTransHisDynamicSqlSupport;
import com.ruoyi.wms.mapper.stock.InvTransHisExtMapper;
import com.ruoyi.wms.mapper.stock.InvTransHisMapper;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 入出库履历Service业务层处理
 *
 * @author ryas
 * created on 2024-02-23
 */
@Service
public class InvTransHisServiceImpl implements IInvTransHisService {

    @Resource
    private InvTransHisMapper invTransHisMapper;
    @Resource
    private InvTransHisExtMapper invTransHisExtMapper;

    /**
     * 查询入出库履历
     *
     * @param invTransNo 入出库履历主键
     * @return 入出库履历
     */
    @Override
    public InvTransHis selectInvTransHisByInvTransNo(String invTransNo) {
        Optional<InvTransHis> result = invTransHisMapper.selectOne(dsl -> dsl.where(InvTransHisDynamicSqlSupport.invTransNo, SqlBuilder.isEqualTo(invTransNo)));
        return result.orElse(null);
    }

    /**
     * 查询入出库履历列表
     *
     * @param invTransHis 入出库履历
     * @return 入出库履历
     */
    @Override
    public List<InvTransHis> selectInvTransHisList(InvTransHis invTransHis) {
        return invTransHisExtMapper.selectPageList(invTransHis);
    }

    /**
     * 批量删除入出库履历
     *
     * @param invTransNos 需要删除的入出库履历主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInvTransHisByInvTransNos(String[] invTransNos) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(InvTransHisDynamicSqlSupport.invTransHis)
                .set(InvTransHisDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(InvTransHisDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(InvTransHisDynamicSqlSupport.updateBy).equalTo(userId)
                .where(InvTransHisDynamicSqlSupport.invTransNo, SqlBuilder.isIn(invTransNos))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return invTransHisMapper.update(provider);
    }

    /**
     * 删除入出库履历信息
     *
     * @param invTransNo 入出库履历主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInvTransHisByInvTransNo(String invTransNo) {
        InvTransHis record = new InvTransHis();
        record.setInvTransNo(invTransNo);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        record.setUpdateTime(DateUtils.getNowDate());
        return invTransHisMapper.updateByPrimaryKey(record);
    }

    /**
     * 新增入出库履历
     *
     * @param stockVo 库存数据
     */
    @Transactional
    @Override
    public void addInvTransHis(StockVo stockVo) {
        if (stockVo == null) {
            throw new IllegalArgumentException("stockVo is null");
        }
        if (stockVo.getStockType() == null || stockVo.getStockType() < 1 || stockVo.getStockType() > 2) {
            throw new IllegalArgumentException("stockType is null or invalid");
        }
        if (StringUtils.isBlank(stockVo.getWhsCd())) {
            throw new IllegalArgumentException("whsCd is blank");
        }
        if (StringUtils.isBlank(stockVo.getStgBinCd())) {
            throw new IllegalArgumentException("stgBinCd is blank");
        }
        if (StringUtils.isBlank(stockVo.getItemCd())) {
            throw new IllegalArgumentException("itemCd is blank");
        }
        if (stockVo.getStdUnitQty() == null) {
            throw new IllegalArgumentException("stdUnitQty is null");
        }
        InvTransHis record = new InvTransHis();
        record.setInvTransType(stockVo.getStockType()); // 入出库类型(1:入库,2:出库)
        record.setWhsCd(stockVo.getWhsCd()); // 仓库代码
        record.setStgBinCd(stockVo.getStgBinCd()); // 货架号
        record.setLotNo(stockVo.getLotNo()); // 批号
        record.setSubLotNo(stockVo.getSubLotNo()); // 子批号
        record.setItemCd(stockVo.getItemCd()); // 物品代码
        record.setStdUnitQty(stockVo.getStdUnitQty()); // 标准单位数量
        record.setPkgUnitQty(stockVo.getPkgUnitQty()); // 包装单位数量
        record.setPalletId(stockVo.getPalletId()); // 托盘ID
        record.setSerialNo(stockVo.getSerialNo()); // 序列号
        record.setBusinessCls(stockVo.getBusinessCls()); // 业务分类
        record.setReason(stockVo.getReason()); // 入出库理由
        record.setTransOrderNo(stockVo.getTransOrderNo()); // 交易单号
        record.setTransOrderDetlNo(stockVo.getTransOrderDetlNo()); // 交易单明细号
        record.setDeptId(stockVo.getDeptId()); // 从属部门ID
        record.setOperator(SecurityUtilsExt.getUsernameFromRedis()); // 操作人
        record.setInvTransNo(SnowFlakeIdGenerator.nextId()); // 入出库履历号
        invTransHisMapper.insertSelective(record);
    }
}
