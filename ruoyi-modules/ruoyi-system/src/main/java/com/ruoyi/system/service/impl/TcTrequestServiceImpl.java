package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TcTrequest;
import com.ruoyi.system.mapper.TcTrequestMapper;
import com.ruoyi.system.service.ITcTrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-26
 */
@Service
public class TcTrequestServiceImpl implements ITcTrequestService 
{
    @Autowired
    private TcTrequestMapper tcTrequestMapper;

    /**
     * 查询交易申请
     * 
     * @param vcTenantId 交易申请主键
     * @return 交易申请
     */
    @Override
    public TcTrequest selectTcTrequestByVcTenantId(String vcTenantId)
    {
        return tcTrequestMapper.selectTcTrequestByVcTenantId(vcTenantId);
    }

    /**
     * 查询交易申请列表
     * 
     * @param tcTrequest 交易申请
     * @return 交易申请
     */
    @Override
    public List<TcTrequest> selectTcTrequestList(TcTrequest tcTrequest)
    {
        return tcTrequestMapper.selectTcTrequestList(tcTrequest);
    }

    /**
     * 新增交易申请
     * 
     * @param tcTrequest 交易申请
     * @return 结果
     */
    @Override
    public int insertTcTrequest(TcTrequest tcTrequest)
    {
        return tcTrequestMapper.insertTcTrequest(tcTrequest);
    }

    /**
     * 修改交易申请
     * 
     * @param tcTrequest 交易申请
     * @return 结果
     */
    @Override
    public int updateTcTrequest(TcTrequest tcTrequest)
    {
        return tcTrequestMapper.updateTcTrequest(tcTrequest);
    }

    /**
     * 批量删除交易申请
     * 
     * @param vcTenantIds 需要删除的交易申请主键
     * @return 结果
     */
    @Override
    public int deleteTcTrequestByVcTenantIds(String[] vcTenantIds)
    {
        return tcTrequestMapper.deleteTcTrequestByVcTenantIds(vcTenantIds);
    }

    /**
     * 删除交易申请信息
     * 
     * @param vcTenantId 交易申请主键
     * @return 结果
     */
    @Override
    public int deleteTcTrequestByVcTenantId(String vcTenantId)
    {
        return tcTrequestMapper.deleteTcTrequestByVcTenantId(vcTenantId);
    }

    @Override
    public  List<TcTrequest> selectMonthEnBalance(String year){
        return tcTrequestMapper.selectMonthEnBalance(year);

    }

}
