package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CompetitionMembersScore;

/**
 * 赛会中-赛程-人员得分Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionMembersScoreService 
{
    /**
     * 查询赛会中-赛程-人员得分
     * 
     * @param id 赛会中-赛程-人员得分主键
     * @return 赛会中-赛程-人员得分
     */
    public CompetitionMembersScore selectCompetitionMembersScoreById(Long id);

    /**
     * 查询赛会中-赛程-人员得分列表
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 赛会中-赛程-人员得分集合
     */
    public List<CompetitionMembersScore> selectCompetitionMembersScoreList(CompetitionMembersScore competitionMembersScore);

    /**
     * 新增赛会中-赛程-人员得分
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 结果
     */
    public int insertCompetitionMembersScore(CompetitionMembersScore competitionMembersScore);

    /**
     * 修改赛会中-赛程-人员得分
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 结果
     */
    public int updateCompetitionMembersScore(CompetitionMembersScore competitionMembersScore);

    /**
     * 批量删除赛会中-赛程-人员得分
     * 
     * @param ids 需要删除的赛会中-赛程-人员得分主键集合
     * @return 结果
     */
    public int deleteCompetitionMembersScoreByIds(Long[] ids);

    /**
     * 删除赛会中-赛程-人员得分信息
     * 
     * @param id 赛会中-赛程-人员得分主键
     * @return 结果
     */
    public int deleteCompetitionMembersScoreById(Long id);
}
