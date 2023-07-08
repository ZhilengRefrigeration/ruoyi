package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.domain.vo.TeamGroupRequest;

/**
 * 赛会中-分组Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionTeamGroupService 
{
    /**
     * 查询赛会中-分组
     * 
     * @param id 赛会中-分组主键
     * @return 赛会中-分组
     */
    public CompetitionTeamGroup selectCompetitionTeamGroupById(Long id);

    /**
     * 查询赛会中-分组列表
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 赛会中-分组集合
     */
    public List<CompetitionTeamGroup> selectCompetitionTeamGroupList(CompetitionTeamGroup competitionTeamGroup);

    /**
     * 新增赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    public int insertCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup);

    /**
     * 修改赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    public int updateCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup);

    /**
     * 批量删除赛会中-分组
     * 
     * @param ids 需要删除的赛会中-分组主键集合
     * @return 结果
     */
    public int deleteCompetitionTeamGroupByIds(Long[] ids);

    /**
     * 删除赛会中-分组信息
     * 
     * @param id 赛会中-分组主键
     * @return 结果
     */
    public int deleteCompetitionTeamGroupById(Long id);
   //赛会中-一键编排分组内的球队的单组循环赛赛程
   public Boolean arrangeTeamGroupSchedule(CompetitionTeamGroup competitionTeamGroup);

    List<CompetitionTeamGroup> getTeamGroupByCondition(CompetitionTeamGroup entity);

    Boolean batchEditTeamGroup(TeamGroupRequest entity);

    Boolean randomTeamGroup(List<TeamGroupRequest> list, Long competitionId);

    Boolean add(CompetitionTeamGroup entity);
}
