package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TokenInfoMapper;
import com.ruoyi.system.domain.TokenInfo;
import com.ruoyi.system.service.ITokenInfoService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class TokenInfoServiceImpl implements ITokenInfoService 
{
    @Autowired
    private TokenInfoMapper tokenInfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TokenInfo selectTokenInfoById(Long id)
    {
        return tokenInfoMapper.selectTokenInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TokenInfo> selectTokenInfoList(TokenInfo tokenInfo)
    {
        return tokenInfoMapper.selectTokenInfoList(tokenInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTokenInfo(TokenInfo tokenInfo)
    {
        return tokenInfoMapper.insertTokenInfo(tokenInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTokenInfo(TokenInfo tokenInfo)
    {
        return tokenInfoMapper.updateTokenInfo(tokenInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTokenInfoByIds(Long[] ids)
    {
        return tokenInfoMapper.deleteTokenInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTokenInfoById(Long id)
    {
        return tokenInfoMapper.deleteTokenInfoById(id);
    }
}
