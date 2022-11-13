package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import com.ruoyi.system.domain.vo.CompetitionUnifiedRecordVo;

/**
 * 赛会中-球队VS球队关系Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionTeamVsTeamService 
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
     * 批量删除赛会中-球队VS球队关系
     * 
     * @param ids 需要删除的赛会中-球队VS球队关系主键集合
     * @return 结果
     */
    public int deleteCompetitionTeamVsTeamByIds(Long[] ids);

    /**
     * 删除赛会中-球队VS球队关系信息
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 结果
     */
    public int deleteCompetitionTeamVsTeamById(Long id);

    public CompetitionUnifiedRecordVo getCompetitionUnifiedRecordById(Long id);
}
