package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.vo.CompetitionMembersScoreVo;
import com.ruoyi.system.domain.vo.PersonalCareerVo;
import org.apache.ibatis.annotations.Param;

/**
 * 赛会中-赛程-人员得分Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionMembersScoreMapper 
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
     * 删除赛会中-赛程-人员得分
     * 
     * @param id 赛会中-赛程-人员得分主键
     * @return 结果
     */
    public int deleteCompetitionMembersScoreById(Long id);

    /**
     * 批量删除赛会中-赛程-人员得分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionMembersScoreByIds(Long[] ids);

    public List<CompetitionMembersScoreVo> findMembersScoreByCompetitionVsId(@Param("competitionId") Long competitionId, @Param("competitionVsId") Long competitionVsId);

    PersonalCareerVo getUserScoreByUserId(@Param(value = "teamUserId") Long teamUserId);

    List<CompetitionMembersScore> getHonorList(@Param("competitionId") Long competitionId,@Param("userId") Long userId);
}
