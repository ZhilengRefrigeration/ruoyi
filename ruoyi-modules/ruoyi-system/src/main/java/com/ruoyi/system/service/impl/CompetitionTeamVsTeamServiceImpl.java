package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import com.ruoyi.system.domain.vo.CompetitionUnifiedRecordVo;
import com.ruoyi.system.mapper.CompetitionMembersScoreMapper;
import com.ruoyi.system.mapper.CompetitionResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 赛会中-球队VS球队关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionTeamVsTeamServiceImpl implements ICompetitionTeamVsTeamService 
{
    @Autowired
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;

    @Resource
    private CompetitionResultMapper competitionResultMapper;
    @Resource
    private CompetitionMembersScoreMapper competitionMembersScoreMapper;

    /**
     * 查询赛会中-球队VS球队关系
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 赛会中-球队VS球队关系
     */
    @Override
    public CompetitionTeamVsTeamVo selectCompetitionTeamVsTeamById(Long id)
    {
        return competitionTeamVsTeamMapper.selectCompetitionTeamVsTeamById(id);
    }

    /**
     * 查询赛会中-球队VS球队关系列表
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 赛会中-球队VS球队关系
     */
    @Override
    public List<CompetitionTeamVsTeamVo> selectCompetitionTeamVsTeamList(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
    }

    /**
     * 新增赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    @Override
    public int insertCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.insertCompetitionTeamVsTeam(competitionTeamVsTeam);
    }

    /**
     * 修改赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    @Override
    public int updateCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(competitionTeamVsTeam);
    }

    /**
     * 批量删除赛会中-球队VS球队关系
     * 
     * @param ids 需要删除的赛会中-球队VS球队关系主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamVsTeamByIds(Long[] ids)
    {
        return competitionTeamVsTeamMapper.deleteCompetitionTeamVsTeamByIds(ids);
    }

    /**
     * 删除赛会中-球队VS球队关系信息
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamVsTeamById(Long id)
    {
        return competitionTeamVsTeamMapper.deleteCompetitionTeamVsTeamById(id);
    }

    @Override
    public CompetitionUnifiedRecordVo getCompetitionUnifiedRecordById(Long id) {
        CompetitionUnifiedRecordVo unifiedRecordVo = new CompetitionUnifiedRecordVo();
        CompetitionTeamVsTeamVo competitionTeamVsTeamVo = competitionTeamVsTeamMapper.getCompetitionById(id);
        if(StringUtils.isEmpty(competitionTeamVsTeamVo)){
            throw new ServiceException("赛程不存在");
        }
        unifiedRecordVo.setTeamVsTeamVo(competitionTeamVsTeamVo);

        //查询队伍数据
        List<CompetitionResult> competitionResultList = competitionResultMapper.findByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());
        unifiedRecordVo.setCompetitionResultList(competitionResultList);

        //查询赛程中个人成绩
        List<CompetitionMembersScore> membersScoreList = competitionMembersScoreMapper.findMembersScoreByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());

        List<CompetitionMembersScore> competitionMembersScoreList = new ArrayList<>();
        //组装队伍球员数据并排序
        //组装队伍球员数据并排序
        if(competitionResultList!=null&&competitionResultList.size()>0){
            //组装主队数据
            List<CompetitionMembersScore> mainMembersScoreList =  membersScoreList.stream().filter(CompetitionMembersScore -> CompetitionMembersScore.getTeamId()==competitionResultList.get(0).getTeamId()).collect(Collectors.toList());
            //过滤首发球员
            List<CompetitionMembersScore> firstList = mainMembersScoreList.stream().filter(a -> a.getIsFirstLaunch() == 1).collect(Collectors.toList());
            firstList.sort((o1, o2) -> o2.getTotalScore().compareTo(o1.getTotalScore()));
            //不是首发球员
            List<CompetitionMembersScore> noFirstList = mainMembersScoreList.stream().filter(a -> a.getIsFirstLaunch() == 0).collect(Collectors.toList());
            //noFirstList.sort((o1, o2) -> o2.getTotalScore().compareTo(o1.getTotalScore()));
            mainMembersScoreList = firstList;
            mainMembersScoreList.addAll(noFirstList);
            competitionMembersScoreList.addAll(mainMembersScoreList);

            //组装客队数据
            List<CompetitionMembersScore> gustMembersScoreList =  membersScoreList.stream().filter(CompetitionMembersScore -> CompetitionMembersScore.getTeamId()==competitionResultList.get(1).getTeamId()).collect(Collectors.toList());
            //过滤首发球员
            List<CompetitionMembersScore> gustFirstList = gustMembersScoreList.stream().filter(a -> a.getIsFirstLaunch() == 1).collect(Collectors.toList());
            gustFirstList.sort((o1, o2) -> o2.getTotalScore().compareTo(o1.getTotalScore()));
            //不是首发球员
            List<CompetitionMembersScore> gustNoFirstList = gustMembersScoreList.stream().filter(a -> a.getIsFirstLaunch() == 0).collect(Collectors.toList());
            //gustNoFirstList.sort((o1, o2) -> o2.getTotalScore().compareTo(o1.getTotalScore()));
            gustMembersScoreList =  gustFirstList;
            gustMembersScoreList.addAll(gustNoFirstList);
            competitionMembersScoreList.addAll(gustMembersScoreList);
            unifiedRecordVo.setCompetitionMembersScoreList(competitionMembersScoreList);
        }
        return unifiedRecordVo;
    }
}
