package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.domain.vo.CompetitionExcleVo;
import com.ruoyi.system.domain.vo.CompetitionResponse;
import com.ruoyi.system.domain.vo.CompetitionVo;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * 比赛信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ICompetitionService extends IService<Competition>
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

    WxAppletsCodeVo genCompetitionCommonAqrSpread(WxAppletsCodeVo wxAppletsCodeVo);

    List<Competition> getCompetitionByCondition(CompetitionVo competition);

    CompetitionExcleVo getTeamEnrollExcleImpData(Long competitionId, Long userId);

    CompetitionResponse getCompetitionById(Long id);

    Boolean establishCompetition(CompetitionVo entity);

    Boolean challengeConfirm(Competition entity);

    List<Competition> getMyJoinCompetition(CompetitionVo entity);

    Boolean add(Competition entity);

    Boolean edit(Competition entity);

    CompetitionExcleVo importExcleData(Long competitionId,Map<String, PictureData> maplist, Sheet sheet);
    //根据当前时间修改已经完成的赛会数据
    int updateCompetitionFinish();
}
