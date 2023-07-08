package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.vo.CompetitionTeamIntegralVo;
import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import org.apache.ibatis.annotations.Param;

/**
 * 赛会中-球队VS球队关系Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionTeamVsTeamMapper 
{
    /**
     * 查询赛会中-球队VS球队关系
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 赛会中-球队VS球队关系
     */
    public CompetitionTeamVsTeamVo selectCompetitionTeamVsTeamById(Long id);

    /**
     * 查询赛会中-球队VS球队关系列表
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 赛会中-球队VS球队关系集合
     */
    public List<CompetitionTeamVsTeamVo> selectCompetitionTeamVsTeamList(CompetitionTeamVsTeam competitionTeamVsTeam);

    /**
     * 新增赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    public int insertCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam);

    /**
     * 修改赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    public int updateCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam);

    /**
     * 删除赛会中-球队VS球队关系
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 结果
     */
    public int deleteCompetitionTeamVsTeamById(Long id);

    /**
     * 批量删除赛会中-球队VS球队关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionTeamVsTeamByIds(Long[] ids);
    /**
     * 批量删除赛会中-球队VS球队关系
     *
     * @param teamVsTeam 需要删除的数据条件
     * @return 结果
     */
    public int updateCompetitionTeamVsTeamByCondition(CompetitionTeamVsTeam teamVsTeam);

    public CompetitionTeamVsTeamVo getCompetitionById(Long id);

    List<CompetitionTeamVsTeamVo> getCompetitionSchedule(CompetitionTeamVsTeam entity);

    List<CompetitionTeamVsTeamVo> getLatelySchedule(CompetitionTeamVsTeam competitionTeamVsTeam);

    List<CompetitionTeamIntegralVo> getCompetitionTeamIntegralListById(@Param("id") Long id);
}
