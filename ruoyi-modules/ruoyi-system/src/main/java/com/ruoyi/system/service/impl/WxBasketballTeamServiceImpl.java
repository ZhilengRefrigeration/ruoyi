package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.system.domain.WxBasketballTeam;
import com.ruoyi.system.mapper.WxBasketballTeamMapper;
import com.ruoyi.system.service.IWxBasketballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 球队管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
public class WxBasketballTeamServiceImpl extends ServiceImpl<WxBasketballTeamMapper, WxBasketballTeam> implements IWxBasketballTeamService
{
    @Autowired
    private WxBasketballTeamMapper wxBasketballTeamMapper;

    /**
     * 查询球队管理
     * 
     * @param id 球队管理主键
     * @return 球队管理
     */
    @Override
    public WxBasketballTeam selectWxBasketballTeamById(Long id)
    {
        return wxBasketballTeamMapper.selectWxBasketballTeamById(id);
    }

    /**
     * 查询球队管理列表
     * 
     * @param wxBasketballTeam 球队管理
     * @return 球队管理
     */
    @Override
    public List<WxBasketballTeam> selectWxBasketballTeamList(WxBasketballTeam wxBasketballTeam)
    {
        return wxBasketballTeamMapper.selectWxBasketballTeamList(wxBasketballTeam);
    }

    /**
     * 新增球队管理
     * 
     * @param wxBasketballTeam 球队管理
     * @return 结果
     */
    @Override
    public int insertWxBasketballTeam(WxBasketballTeam wxBasketballTeam)
    {
        return wxBasketballTeamMapper.insertWxBasketballTeam(wxBasketballTeam);
    }

    /**
     * 修改球队管理
     * 
     * @param wxBasketballTeam 球队管理
     * @return 结果
     */
    @Override
    public int updateWxBasketballTeam(WxBasketballTeam wxBasketballTeam)
    {
        return wxBasketballTeamMapper.updateWxBasketballTeam(wxBasketballTeam);
    }

    /**
     * 批量删除球队管理
     * 
     * @param ids 需要删除的球队管理主键
     * @return 结果
     */
    @Override
    public int deleteWxBasketballTeamByIds(Long[] ids)
    {
        return wxBasketballTeamMapper.deleteWxBasketballTeamByIds(ids);
    }

    /**
     * 删除球队管理信息
     * 
     * @param id 球队管理主键
     * @return 结果
     */
    @Override
    public int deleteWxBasketballTeamById(Long id)
    {
        return wxBasketballTeamMapper.deleteWxBasketballTeamById(id);
    }

    @Override
    public List<WxBasketballTeam> getMyBasketBallTeam(WxBasketballTeam entity) {
        return wxBasketballTeamMapper.getMyBasketBallTeam(entity);
    }
}
