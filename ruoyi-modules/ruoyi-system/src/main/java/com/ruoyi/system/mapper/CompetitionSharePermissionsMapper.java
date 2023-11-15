package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.CompetitionSharePermissions;

import java.util.List;

/**
 * 赛会-权限分享Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
public interface CompetitionSharePermissionsMapper extends BaseMapper<CompetitionSharePermissions>
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
     * 删除赛会-权限分享
     * 
     * @param id 赛会-权限分享主键
     * @return 结果
     */
    public int deleteCompetitionSharePermissionsById(Long id);

    /**
     * 批量删除赛会-权限分享
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionSharePermissionsByIds(Long[] ids);

    List<CompetitionSharePermissions> getList(CompetitionSharePermissions competitionSharePermissions);
}
