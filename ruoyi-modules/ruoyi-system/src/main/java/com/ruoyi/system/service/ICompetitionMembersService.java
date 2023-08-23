package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.domain.vo.CompetitionMembersVo;

/**
 * 比赛参与人员Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionMembersService 
{
    /**
     * 查询比赛参与人员
     * 
     * @param id 比赛参与人员主键
     * @return 比赛参与人员
     */
    public CompetitionMembers selectCompetitionMembersById(Long id);

    /**
     * 查询比赛参与人员列表
     * 
     * @param competitionMembers 比赛参与人员
     * @return 比赛参与人员集合
     */
    public List<CompetitionMembers> selectCompetitionMembersList(CompetitionMembers competitionMembers);

    /**
     * 新增比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    public int insertCompetitionMembers(CompetitionMembers competitionMembers);

    /**
     * 修改比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    public int updateCompetitionMembers(CompetitionMembers competitionMembers);

    /**
     * 批量删除比赛参与人员
     * 
     * @param ids 需要删除的比赛参与人员主键集合
     * @return 结果
     */
    public int deleteCompetitionMembersByIds(Long[] ids);

    /**
     * 删除比赛参与人员信息
     * 
     * @param id 比赛参与人员主键
     * @return 结果
     */
    public int deleteCompetitionMembersById(Long id);

    List<CompetitionMembersVo> getJoinCompetitionMembersPage(CompetitionMembersVo entity);

    void deleteByMembers(Long competitionId, Long teamOfId);

    void bindCompetitionMembersByTel(Long userId, String telephone);

    CompetitionMembersVo getCompetitionUserScoreInfo(Long id);
}
