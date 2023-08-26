package com.ruoyi.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.ruoyi.system.domain.BuildingTeamRel;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface BuildingTeamRelMapper extends BaseMapper<BuildingTeamRel>
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingTeamRelById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBuildingTeamRelByIds(Long[] ids);
}
