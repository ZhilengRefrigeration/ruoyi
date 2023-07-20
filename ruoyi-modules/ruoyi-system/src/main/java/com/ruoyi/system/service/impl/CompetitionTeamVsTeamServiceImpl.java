package com.ruoyi.system.service.impl;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.CompetitionMembersScoreMapper;
import com.ruoyi.system.mapper.CompetitionResultMapper;
import com.ruoyi.system.service.ICompetitionResultService;
import com.ruoyi.system.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;
import org.springframework.transaction.annotation.Transactional;
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
    private ICompetitionResultService competitionResultService;
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
        List<CompetitionResultVo> competitionResultList = competitionResultService.findByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());
        unifiedRecordVo.setCompetitionResultList(competitionResultList);

        //查询赛程中个人成绩
        List<CompetitionMembersScoreVo> membersScoreList = competitionMembersScoreMapper.findMembersScoreByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());

        List<CompetitionMembersScore> competitionMembersScoreList = new ArrayList<>();
        //组装队伍球员数据并排序
        //组装队伍球员数据并排序
        if(competitionResultList!=null&&competitionResultList.size()>0){
            //组装主队数据
            List<CompetitionMembersScore> mainMembersScoreList =  membersScoreList.stream().filter(CompetitionMembersScore -> CompetitionMembersScore.getCompetitionOfTeamId()==competitionResultList.get(0).getCompetitionOfTeamId()).collect(Collectors.toList());
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
            List<CompetitionMembersScore> gustMembersScoreList =  membersScoreList.stream().filter(CompetitionMembersScore -> CompetitionMembersScore.getCompetitionOfTeamId()==competitionResultList.get(1).getCompetitionOfTeamId()).collect(Collectors.toList());
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

    @Override
    public CompetitionVsRecordVo getCompetitionVsRecordById(Long id) {
        CompetitionVsRecordVo recordVo = new CompetitionVsRecordVo();
        CompetitionTeamVsTeamVo competitionTeamVsTeamVo = competitionTeamVsTeamMapper.getCompetitionById(id);
        if(StringUtils.isEmpty(competitionTeamVsTeamVo)){
            throw new ServiceException("赛程不存在");
        }
        //获取主队每节数据
        List<CompetitionResultVo> competitionResultList = competitionResultService.findByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());
        Optional<CompetitionResultVo> main = competitionResultList.stream().filter(a -> a.getCompetitionOfTeamId().equals(competitionTeamVsTeamVo.getMainTeamId())).findFirst();
        Optional<CompetitionResultVo> guest = competitionResultList.stream().filter(a -> a.getCompetitionOfTeamId().equals(competitionTeamVsTeamVo.getGuestTeamId())).findFirst();
        List<CompetitionMembersScoreVo> membersScoreList = competitionMembersScoreMapper.findMembersScoreByCompetitionVsId(competitionTeamVsTeamVo.getCompetitionId(),competitionTeamVsTeamVo.getId());
        if(main.isPresent()){
            CompetitionResultVo resultVo =  main.get();
            List<CompetitionMembersScoreVo> membersScores = membersScoreList.stream().filter(a -> a.getCompetitionOfTeamId().equals(competitionTeamVsTeamVo.getMainTeamId())).collect(Collectors.toList());
            resultVo.setMembersScoreList(membersScores);
            recordVo.setMainTeam(resultVo);
        }
        if(guest.isPresent()){
            CompetitionResultVo resultVo = guest.get();
            List<CompetitionMembersScoreVo> membersScores = membersScoreList.stream().filter(a -> a.getCompetitionOfTeamId().equals(competitionTeamVsTeamVo.getGuestTeamId())).collect(Collectors.toList());
            resultVo.setMembersScoreList(membersScores);
            recordVo.setGuestTeam(resultVo);
        }
        recordVo.setTeamVsTeamVo(competitionTeamVsTeamVo);
        return recordVo;
    }

    @Override
    public Map<String, List<CompetitionTeamVsTeamVo>> getCompetitionSchedule(CompetitionTeamVsTeam entity) {
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamMapper.getCompetitionSchedule(entity);
        //Java8 stream根据字段分组并排序
        //1.根据字符串类型日期分组，并按照日期升序排序，返回TreeMap<String,List>，map的key为字符串日期，value为list
        TreeMap<String,List<CompetitionTeamVsTeamVo>> dataGroupMap = list.stream().collect(Collectors.groupingBy(a->a.getCompetitionDate(), TreeMap::new,Collectors.toList()));
        // 2.将value中的list按照时间早晚排序
        Set<Map.Entry<String,List<CompetitionTeamVsTeamVo>>> entrySet = dataGroupMap.entrySet();
        for(Map.Entry<String,List<CompetitionTeamVsTeamVo>> entry : entrySet){
            List<CompetitionTeamVsTeamVo> sortedList =  entry.getValue().stream().sorted(Comparator.comparing(CompetitionTeamVsTeamVo :: getTheTime)).collect(Collectors.toList());
        }
        return dataGroupMap;
    }

    @Override
    public List<CompetitionTeamIntegralVo> getCompetitionTeamIntegralListById(CompetitionTeamIntegralVo vo) {
        return competitionTeamVsTeamMapper.getCompetitionTeamIntegralListById(vo);
    }
    @Transactional
    @Override
    public Boolean competitionScheduleSubmit(List<CompetitionTeamVsTeamRequest> vsTeamRequestList) {
        LoginUser user = SecurityUtils.getLoginUser();
        List<CompetitionTeamVsTeam> allList = new ArrayList<>();
        //校验数据
        for (CompetitionTeamVsTeamRequest request : vsTeamRequestList) {
            if(StringUtils.isEmpty(request.getCompetitionId())){
                throw new InvalidParameterException("参数competitionId不能为空");
            }else if(StringUtils.isEmpty(request.getCompetitionDate())){
                throw new InvalidParameterException("比赛日期[competitionDate]不能为空");
            }else if(StringUtils.isEmpty(request.getTeamVsTeamList())||request.getTeamVsTeamList().size()==0){
                throw new InvalidParameterException("赛程对阵[teamVsTeamList]不能为空");
            }
            for (CompetitionTeamVsTeamVo vo: request.getTeamVsTeamList()) {
                if(StringUtils.isEmpty(vo.getMainTeamId())){
                    throw new InvalidParameterException("主队[mainTeamId]不能为空");
                }if(StringUtils.isEmpty(vo.getTheTime())){
                    throw new InvalidParameterException("比赛时间[theTime]不能为空");
                }else if(StringUtils.isEmpty(vo.getMainTeamName())){
                    throw new InvalidParameterException("主队[mainTeamName]不能为空");
                }else if(StringUtils.isEmpty(vo.getGuestTeamId())){
                    throw new InvalidParameterException("客队[guestTeamId]不能为空");
                }else if(StringUtils.isEmpty(vo.getGuestTeamName())){
                    throw new InvalidParameterException("客队[guestTeamName]不能为空");
                }else if(StringUtils.isEmpty(vo.getBuildingId())){
                    throw new InvalidParameterException("比赛场地[buildingId]不能为空");
                }else if(StringUtils.isEmpty(vo.getBuildingName())){
                    throw new InvalidParameterException("比赛场地[buildingName]不能为空");
                }else if(StringUtils.isEmpty(vo.getMainTeamLogo())){
                    throw new InvalidParameterException("主队logo[mainTeamLogo]不能为空");
                }else if(StringUtils.isEmpty(vo.getGuestTeamLogo())){
                    throw new InvalidParameterException("客队logo[guestTeamLogo]不能为空");
                }
                vo.setCreatedBy(String.valueOf(user.getUserid()));
                vo.setCreatedTime(new Date());
                vo.setVsType(request.getVsType());
                vo.setCompetitionId(request.getCompetitionId());
                if(ObjectUtil.isNull(vo.getId())){
                    competitionTeamVsTeamMapper.insertCompetitionTeamVsTeam(vo);
                }else {
                    competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(vo);
                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean competitionScoreSubmit(List<CompetitionResult> request) {

        for(CompetitionResult competitionResult : request){
            //小节分数可能为空
            int oneNodeScore = competitionResult.getOneNodeScore()==null?0:competitionResult.getOneNodeScore();
            int twoNodeScore = competitionResult.getTwoNodeScore()==null?0:competitionResult.getTwoNodeScore();
            int threeNodeScore = competitionResult.getThreeNodeScore()==null?0:competitionResult.getThreeNodeScore();
            int fourNodeScore = competitionResult.getFourNodeScore()==null?0:competitionResult.getFourNodeScore();
            int fiveNodeScore = competitionResult.getFiveNodeScore()==null?0:competitionResult.getFiveNodeScore();
            int sixNodeScore = competitionResult.getSixNodeScore()==null?0:competitionResult.getSixNodeScore();

            int score = oneNodeScore+twoNodeScore+threeNodeScore+fourNodeScore+fiveNodeScore+sixNodeScore;
            if(score>0&&competitionResult.getTotalScore()!=null){
                if(score!=competitionResult.getTotalScore()){
                    throw new InvalidParameterException("总分和小节分之和 不等 请检查！");
                }
            } else if(score==0&&competitionResult.getTotalScore()!=null){
                competitionResult.setOneNodeScore(competitionResult.getTotalScore());
            }

            //新增
            if(competitionResult.getId()==null){
                competitionResultService.add(competitionResult);
            } else {//编辑
                if(StringUtils.isEmpty(competitionResult.getId())){
                    throw new InvalidParameterException("id为空");
                }
                competitionResultService.edit(competitionResult);
            }
        }
        return true;
    }

    @Override
    public Boolean deleteBatchByIds(Ids ids) {
        Long[]  idList = ids.getIdList().stream().toArray(Long[]::new);
        competitionTeamVsTeamMapper.deleteCompetitionTeamVsTeamByIds(idList);
        return Boolean.TRUE;
    }

    @Override
    public int competitionVsTeamStatusUpdate(CompetitionTeamVsTeam competitionTeamVsTeam) {
        return competitionTeamVsTeamMapper.competitionVsTeamStatusUpdate(competitionTeamVsTeam);
    }

    @Override
    public List<CompetitionTeamVsTeamVo> getTodaySchedule(CompetitionTeamVsTeam competitionTeamVsTeam) {
        return competitionTeamVsTeamMapper.getTodaySchedule(competitionResultService);
    }
}
