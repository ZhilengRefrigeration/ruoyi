package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.vo.BegerArrangementVo;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import com.ruoyi.system.mapper.CompetitionMapper;
import com.ruoyi.system.mapper.CompetitionOfTeamMapper;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import com.ruoyi.system.utils.BegerSingleCycleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionTeamGroupMapper;
import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.service.ICompetitionTeamGroupService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 赛会中-分组Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionTeamGroupServiceImpl implements ICompetitionTeamGroupService 
{
    @Resource
    private CompetitionTeamGroupMapper competitionTeamGroupMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private CompetitionMapper competitionMapper;
    @Resource
    private CompetitionOfTeamMapper competitionOfTeamMapper;
    @Resource
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;
    /**
     * 查询赛会中-分组
     * 
     * @param id 赛会中-分组主键
     * @return 赛会中-分组
     */
    @Override
    public CompetitionTeamGroup selectCompetitionTeamGroupById(Long id)
    {
        return competitionTeamGroupMapper.selectCompetitionTeamGroupById(id);
    }

    /**
     * 查询赛会中-分组列表
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 赛会中-分组
     */
    @Override
    public List<CompetitionTeamGroup> selectCompetitionTeamGroupList(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.selectCompetitionTeamGroupList(competitionTeamGroup);
    }

    /**
     * 新增赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    @Override
    public int insertCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.insertCompetitionTeamGroup(competitionTeamGroup);
    }

    /**
     * 修改赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    @Override
    public int updateCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.updateCompetitionTeamGroup(competitionTeamGroup);
    }

    /**
     * 批量删除赛会中-分组
     * 
     * @param ids 需要删除的赛会中-分组主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamGroupByIds(Long[] ids)
    {
        return competitionTeamGroupMapper.deleteCompetitionTeamGroupByIds(ids);
    }

    /**
     * 删除赛会中-分组信息
     * 
     * @param id 赛会中-分组主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamGroupById(Long id)
    {
        return competitionTeamGroupMapper.deleteCompetitionTeamGroupById(id);
    }

    @Override
    public Boolean arrangeTeamGroupSchedule(CompetitionTeamGroup competitionTeamGroup) {
        //todo 加锁防止重复的点编排赛程
        Object isLock = redisService.getCacheObject(CacheConstants.ARRANGE_TEAM_GROUP_SCHEDULE + competitionTeamGroup.getId());
        if(!StringUtils.isEmpty(isLock)){
            throw new ServiceException("系统正在努力的对当前分组的球队进行赛程编排，请稍等片刻");
        }else {
            Boolean bool =false;
            CompetitionTeamGroup teamGroup = competitionTeamGroupMapper.selectCompetitionTeamGroupById(competitionTeamGroup.getId());
            if(teamGroup.getIsCycle().intValue()==1){
                throw new ServiceException("当前分组中的球队已经编排过了。无需重复操作");
            }
            //获取比赛时间
            Competition competition =  competitionMapper.selectCompetitionById(teamGroup.getCompetitionId());
            Date competitionBeginTime = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(competitionBeginTime);
            calendar.add(Calendar.DATE,5); //把日期往后增加一天,整数  往后推,负数往前移动
            //按报名优先级来排序
            CompetitionOfTeam ofTeam = new CompetitionOfTeam();
            ofTeam.setCompetitionId(teamGroup.getCompetitionId());
            ofTeam.setCompetitionGroup(teamGroup.getCompetitionGroup());
            ofTeam.setIsDeleted(0L);
            List<CompetitionOfTeamVo> groupTeamList = competitionOfTeamMapper.selectCompetitionOfTeamList(ofTeam);
            //todo 对球队做循环赛算法
            if(groupTeamList.size()>0) {
                List<CompetitionTeamVsTeam> allList = new ArrayList<>();
                List<BegerArrangementVo> vsListVo = BegerSingleCycleUtil.begerSingleCycle(groupTeamList);
                for (BegerArrangementVo vo:vsListVo){
                    List<CompetitionTeamVsTeam> vsTeams = vo.getVsTeamList();
                    calendar.add(Calendar.DATE,1);
                    for(CompetitionTeamVsTeam teamVsTeam:vsTeams){
                        teamVsTeam.setCompetitionTime(calendar.getTime());
                        competitionTeamVsTeamMapper.insertCompetitionTeamVsTeam(teamVsTeam);
                    }
                    allList.addAll(vsTeams);
                }
                //System.out.println(JSON.toJSONString(allList));
                if(allList.size()>0) {
                    //todo 如果status = 1 表示 分组内的赛程重新编排，需要删除原来的
                    if(!StringUtils.isEmpty(competitionTeamGroup.getStatus())){
                        if(competitionTeamGroup.getStatus().intValue() == 1 ){
                            CompetitionTeamVsTeam competitionTeamVsTeam = new CompetitionTeamVsTeam();
                            competitionTeamVsTeam.setCompetitionId(teamGroup.getCompetitionId());
                            competitionTeamVsTeam.setCompetitionGroup(teamGroup.getCompetitionGroup());
                            competitionTeamVsTeam.setVsType("循环赛");
                            competitionTeamVsTeamMapper.updateCompetitionTeamVsTeamByCondition(competitionTeamVsTeam);
                        }
                    }
                }
                CompetitionTeamGroup teamGroup2 = new CompetitionTeamGroup();
                teamGroup2.setId(teamGroup.getId());
                teamGroup2.setIsCycle(1L);
                competitionTeamGroupMapper.updateCompetitionTeamGroup(teamGroup2);
            }else {
                throw new ServiceException("当前分组中暂无球队数据");
            }
            redisService.setCacheObject(CacheConstants.ARRANGE_TEAM_GROUP_SCHEDULE+competitionTeamGroup.getId(),true,10L, TimeUnit.SECONDS);
        }
        return Boolean.TRUE;
    }
}
