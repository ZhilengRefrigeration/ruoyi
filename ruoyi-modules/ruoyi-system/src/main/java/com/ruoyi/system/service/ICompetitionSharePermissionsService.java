package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.CompetitionSharePermissions;

import java.util.List;

/**
 * 赛会-权限分享Service接口
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
public interface ICompetitionSharePermissionsService extends IService<CompetitionSharePermissions>
{
    /**
     * 查询赛会-权限分享
     * 
     * @param id 赛会-权限分享主键
     * @return 赛会-权限分享
     */
    public CompetitionSharePermissions selectCompetitionSharePermissionsById(Long id);

    /**
     * 查询赛会-权限分享列表
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 赛会-权限分享集合
     */
    public List<CompetitionSharePermissions> selectCompetitionSharePermissionsList(CompetitionSharePermissions competitionSharePermissions);

    /**
     * 新增赛会-权限分享
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 结果
     */
    public int insertCompetitionSharePermissions(CompetitionSharePermissions competitionSharePermissions);

    /**
     * 修改赛会-权限分享
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 结果
     */
    public int updateCompetitionSharePermissions(CompetitionSharePermissions competitionSharePermissions);

    /**
     * 批量删除赛会-权限分享
     * 
     * @param ids 需要删除的赛会-权限分享主键集合
     * @return 结果
     */
    public int deleteCompetitionSharePermissionsByIds(Long[] ids);

    /**
     * 删除赛会-权限分享信息
     * 
     * @param id 赛会-权限分享主键
     * @return 结果
     */
    public int deleteCompetitionSharePermissionsById(Long id);

    Boolean shareCompetitionPermissions(CompetitionSharePermissions vo);

    Boolean delShareCompetitionPermissions(Long[] ids);

    List<CompetitionSharePermissions> getList(CompetitionSharePermissions competitionSharePermissions);
}
