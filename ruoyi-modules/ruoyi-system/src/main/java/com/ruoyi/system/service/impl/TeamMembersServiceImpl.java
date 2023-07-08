package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.TeamMembersResponse;
import com.ruoyi.system.domain.vo.TeamMembersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamMembersMapper;
import com.ruoyi.system.domain.TeamMembers;
import com.ruoyi.system.service.ITeamMembersService;

/**
 * 球队人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class TeamMembersServiceImpl implements ITeamMembersService 
{
    @Autowired
    private TeamMembersMapper teamMembersMapper;

    /**
     * 查询球队人员
     * 
     * @param id 球队人员主键
     * @return 球队人员
     */
    @Override
    public TeamMembers selectTeamMembersById(Long id)
    {
        return teamMembersMapper.selectTeamMembersById(id);
    }

    /**
     * 查询球队人员列表
     * 
     * @param teamMembers 球队人员
     * @return 球队人员
     */
    @Override
    public List<TeamMembersVo> selectTeamMembersList(TeamMembersVo teamMembers)
    {
        return teamMembersMapper.selectTeamMembersList(teamMembers);
    }

    /**
     * 新增球队人员
     * 
     * @param teamMembers 球队人员
     * @return 结果
     */
    @Override
    public int insertTeamMembers(TeamMembers teamMembers)
    {
        return teamMembersMapper.insertTeamMembers(teamMembers);
    }

    /**
     * 修改球队人员
     * 
     * @param teamMembers 球队人员
     * @return 结果
     */
    @Override
    public int updateTeamMembers(TeamMembers teamMembers)
    {
        return teamMembersMapper.updateTeamMembers(teamMembers);
    }

    /**
     * 批量删除球队人员
     * 
     * @param ids 需要删除的球队人员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembersByIds(Long[] ids)
    {
        return teamMembersMapper.deleteTeamMembersByIds(ids);
    }

    /**
     * 删除球队人员信息
     * 
     * @param id 球队人员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembersById(Long id)
    {
        return teamMembersMapper.deleteTeamMembersById(id);
    }

    @Override
    public List<TeamMembersResponse> getTeamMembersByTeamId(Long teamId) {
        return teamMembersMapper.getTeamMembersByTeamId(teamId);
    }

    @Override
    public TeamMembers getOneByTeamIdAndRoleCode(Long guestTeamId, String code) {
        return teamMembersMapper.getOneByTeamIdAndRoleCode(guestTeamId,code);
    }
}
