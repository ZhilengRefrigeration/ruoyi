package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.domain.vo.CompetitionMembersVo;
import org.apache.ibatis.annotations.Param;

/**
 * 比赛参与人员Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionMembersMapper 
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
     * 删除比赛参与人员
     * 
     * @param id 比赛参与人员主键
     * @return 结果
     */
    public int deleteCompetitionMembersById(Long id);

    /**
     * 批量删除比赛参与人员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionMembersByIds(Long[] ids);

    List<CompetitionMembersVo> getJoinCompetitionMembersPage(CompetitionMembersVo entity);

    void deleteByMembers(Long competitionId, Long teamOfId);

    List<CompetitionMembersVo> getCompetitionMembersByCompetitionId(Long competitionId);

    void bindCompetitionMembersByTel(@Param(value = "userId") Long userId,@Param(value = "telephone")  String telephone);
}
