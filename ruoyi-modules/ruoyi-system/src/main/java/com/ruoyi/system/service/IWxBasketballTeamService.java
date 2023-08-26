package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.WxBasketballTeam;

import java.util.List;

/**
 * 球队管理Service接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface IWxBasketballTeamService extends IService<WxBasketballTeam>
{
    /**
     * 查询球队管理
     * 
     * @param id 球队管理主键
     * @return 球队管理
     */
    public WxBasketballTeam selectWxBasketballTeamById(Long id);

    /**
     * 查询球队管理列表
     * 
     * @param wxBasketballTeam 球队管理
     * @return 球队管理集合
     */
    public List<WxBasketballTeam> selectWxBasketballTeamList(WxBasketballTeam wxBasketballTeam);

    /**
     * 新增球队管理
     * 
     * @param wxBasketballTeam 球队管理
     * @return 结果
     */
    public int insertWxBasketballTeam(WxBasketballTeam wxBasketballTeam);

    /**
     * 修改球队管理
     * 
     * @param wxBasketballTeam 球队管理
     * @return 结果
     */
    public int updateWxBasketballTeam(WxBasketballTeam wxBasketballTeam);

    /**
     * 批量删除球队管理
     * 
     * @param ids 需要删除的球队管理主键集合
     * @return 结果
     */
    public int deleteWxBasketballTeamByIds(Long[] ids);

    /**
     * 删除球队管理信息
     * 
     * @param id 球队管理主键
     * @return 结果
     */
    public int deleteWxBasketballTeamById(Long id);

    List<WxBasketballTeam> getMyBasketBallTeam(WxBasketballTeam entity);
}
