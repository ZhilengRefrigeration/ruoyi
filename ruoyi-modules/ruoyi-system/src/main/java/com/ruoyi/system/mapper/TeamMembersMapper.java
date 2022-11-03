package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TeamMembers;
import com.ruoyi.system.domain.vo.TeamMembersVo;

/**
 * 球队人员Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface TeamMembersMapper 
{
    /**
     * 查询球队人员
     * 
     * @param id 球队人员主键
     * @return 球队人员
     */
    public TeamMembers selectTeamMembersById(Long id);

    /**
     * 查询球队人员列表
     * 
     * @param teamMembers 球队人员
     * @return 球队人员集合
     */
    public List<TeamMembersVo> selectTeamMembersList(TeamMembersVo teamMembers);

    /**
     * 新增球队人员
     * 
     * @param teamMembers 球队人员
     * @return 结果
     */
    public int insertTeamMembers(TeamMembers teamMembers);

    /**
     * 修改球队人员
     * 
     * @param teamMembers 球队人员
     * @return 结果
     */
    public int updateTeamMembers(TeamMembers teamMembers);

    /**
     * 删除球队人员
     * 
     * @param id 球队人员主键
     * @return 结果
     */
    public int deleteTeamMembersById(Long id);

    /**
     * 批量删除球队人员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamMembersByIds(Long[] ids);
}
