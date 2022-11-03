package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionTeamGroup;

/**
 * 赛会中-分组Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionTeamGroupMapper 
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
     * 删除赛会中-分组
     * 
     * @param id 赛会中-分组主键
     * @return 结果
     */
    public int deleteCompetitionTeamGroupById(Long id);

    /**
     * 批量删除赛会中-分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionTeamGroupByIds(Long[] ids);
}
