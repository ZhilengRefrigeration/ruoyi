package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxBasketballTeam;
import org.apache.ibatis.annotations.Param;

/**
 * 球队管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface WxBasketballTeamMapper 
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
     * 删除球队管理
     * 
     * @param id 球队管理主键
     * @return 结果
     */
    public int deleteWxBasketballTeamById(Long id);

    /**
     * 批量删除球队管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxBasketballTeamByIds(Long[] ids);

    List<WxBasketballTeam> getMyBasketBallTeam(WxBasketballTeam entity);

    List<WxBasketballTeam> selectBatchIds(@Param("teamIds")  List<Long> teamIds);
}
