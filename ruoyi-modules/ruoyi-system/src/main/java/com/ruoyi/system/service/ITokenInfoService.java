package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TokenInfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface ITokenInfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TokenInfo selectTokenInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TokenInfo> selectTokenInfoList(TokenInfo tokenInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertTokenInfo(TokenInfo tokenInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tokenInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateTokenInfo(TokenInfo tokenInfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTokenInfoByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTokenInfoById(Long id);
}
