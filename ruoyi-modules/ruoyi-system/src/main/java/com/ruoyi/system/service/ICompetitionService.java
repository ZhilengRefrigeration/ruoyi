package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Competition;

/**
 * 比赛信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ICompetitionService 
{
    /**
     * 查询比赛信息
     * 
     * @param id 比赛信息主键
     * @return 比赛信息
     */
    public Competition selectCompetitionById(Long id);

    /**
     * 查询比赛信息列表
     * 
     * @param competition 比赛信息
     * @return 比赛信息集合
     */
    public List<Competition> selectCompetitionList(Competition competition);

    /**
     * 新增比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    public int insertCompetition(Competition competition);

    /**
     * 修改比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    public int updateCompetition(Competition competition);

    /**
     * 批量删除比赛信息
     * 
     * @param ids 需要删除的比赛信息主键集合
     * @return 结果
     */
    public int deleteCompetitionByIds(Long[] ids);

    /**
     * 删除比赛信息信息
     * 
     * @param id 比赛信息主键
     * @return 结果
     */
    public int deleteCompetitionById(Long id);
}
