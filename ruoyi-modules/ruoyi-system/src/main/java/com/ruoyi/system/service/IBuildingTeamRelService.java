package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.BuildingTeamRel;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface IBuildingTeamRelService extends IService<BuildingTeamRel>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BuildingTeamRel selectBuildingTeamRelById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BuildingTeamRel> selectBuildingTeamRelList(BuildingTeamRel buildingTeamRel);

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 结果
     */
    public int insertBuildingTeamRel(BuildingTeamRel buildingTeamRel);

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 结果
     */
    public int updateBuildingTeamRel(BuildingTeamRel buildingTeamRel);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBuildingTeamRelByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingTeamRelById(Long id);
}
