package com.ruoyi.system.service;

import com.ruoyi.system.domain.TcTrequest;

import java.util.List;

/**
 * 交易申请Service接口
 * 
 * @author ruoyi
 * @date 2023-07-26
 */
public interface ITcTrequestService 
{
    /**
     * 查询交易申请
     * 
     * @param vcTenantId 交易申请主键
     * @return 交易申请
     */
    public TcTrequest selectTcTrequestByVcTenantId(String vcTenantId);

    /**
     * 查询交易申请列表
     * 
     * @param tcTrequest 交易申请
     * @return 交易申请集合
     */
    public List<TcTrequest> selectTcTrequestList(TcTrequest tcTrequest);

    /**
     * 新增交易申请
     * 
     * @param tcTrequest 交易申请
     * @return 结果
     */
    public int insertTcTrequest(TcTrequest tcTrequest);

    /**
     * 修改交易申请
     * 
     * @param tcTrequest 交易申请
     * @return 结果
     */
    public int updateTcTrequest(TcTrequest tcTrequest);

    /**
     * 批量删除交易申请
     * 
     * @param vcTenantIds 需要删除的交易申请主键集合
     * @return 结果
     */
    public int deleteTcTrequestByVcTenantIds(String[] vcTenantIds);

    /**
     * 删除交易申请信息
     * 
     * @param vcTenantId 交易申请主键
     * @return 结果
     */
    public int deleteTcTrequestByVcTenantId(String vcTenantId);

    List<TcTrequest> selectMonthEnBalance(String year);
}
