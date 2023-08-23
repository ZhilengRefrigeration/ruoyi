package com.ruoyi.system.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.system.domain.vo.CompetitionMembersScoreVo;
import com.ruoyi.system.domain.vo.CompetitionMembersVo;
import com.ruoyi.system.domain.vo.PersonalCareerVo;
import com.ruoyi.system.service.ICompetitionMembersScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionMembersMapper;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.service.ICompetitionMembersService;

/**
 * 比赛参与人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionMembersServiceImpl implements ICompetitionMembersService 
{
    @Autowired
    private CompetitionMembersMapper competitionMembersMapper;
    @Autowired
    private ICompetitionMembersScoreService competitionMembersScoreService;

    /**
     * 查询比赛参与人员
     * 
     * @param id 比赛参与人员主键
     * @return 比赛参与人员
     */
    @Override
    public CompetitionMembers selectCompetitionMembersById(Long id)
    {
        return competitionMembersMapper.selectCompetitionMembersById(id);
    }

    /**
     * 查询比赛参与人员列表
     * 
     * @param competitionMembers 比赛参与人员
     * @return 比赛参与人员
     */
    @Override
    public List<CompetitionMembers> selectCompetitionMembersList(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.selectCompetitionMembersList(competitionMembers);
    }

    /**
     * 新增比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    @Override
    public int insertCompetitionMembers(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.insertCompetitionMembers(competitionMembers);
    }

    /**
     * 修改比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    @Override
    public int updateCompetitionMembers(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.updateCompetitionMembers(competitionMembers);
    }

    /**
     * 批量删除比赛参与人员
     * 
     * @param ids 需要删除的比赛参与人员主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersByIds(Long[] ids)
    {
        return competitionMembersMapper.deleteCompetitionMembersByIds(ids);
    }

    /**
     * 删除比赛参与人员信息
     * 
     * @param id 比赛参与人员主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersById(Long id)
    {
        return competitionMembersMapper.deleteCompetitionMembersById(id);
    }

    @Override
    public List<CompetitionMembersVo> getJoinCompetitionMembersPage(CompetitionMembersVo entity) {
        return competitionMembersMapper.getJoinCompetitionMembersPage(entity);
    }

    @Override
    public void deleteByMembers(Long competitionId, Long teamOfId) {
        competitionMembersMapper.deleteByMembers(competitionId,teamOfId);
    }

    @Override
    public void bindCompetitionMembersByTel(Long userId, String telephone) {
        competitionMembersMapper.bindCompetitionMembersByTel(userId,telephone);
    }

    @Override
    public CompetitionMembersVo getCompetitionUserScoreInfo(Long id) {
        CompetitionMembersVo membersVo = new CompetitionMembersVo();
        CompetitionMembers member = competitionMembersMapper.selectCompetitionMembersById(id);
        BeanUtil.copyProperties(member,membersVo);
        //获取本赛会的个人得分情况
        CompetitionMembersScoreVo membersScoreVo = competitionMembersScoreService.getThisCompetitionScore(member.getCompetitionId(),member.getId());
        membersVo.setCompetitionMemberScore(membersScoreVo);
        //如果没有登录我们的系统的人员就无法统计职业生涯
        if(ObjectUtil.isNotEmpty(member.getUserId())){
            //个人生涯
            PersonalCareerVo personalCareerVo = competitionMembersScoreService.getUserScoreByUserId(member.getUserId());
            membersVo.setPersonalCareerVo(personalCareerVo);
        }
        return membersVo;
    }
}
