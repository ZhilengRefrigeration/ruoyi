package com.ruoyi.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.ruoyi.system.domain.UserBuildingRel;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface UserBuildingRelMapper extends BaseMapper<UserBuildingRel>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserBuildingRel selectUserBuildingRelById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserBuildingRel> selectUserBuildingRelList(UserBuildingRel userBuildingRel);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 结果
     */
    public int insertUserBuildingRel(UserBuildingRel userBuildingRel);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 结果
     */
    public int updateUserBuildingRel(UserBuildingRel userBuildingRel);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserBuildingRelById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBuildingRelByIds(Long[] ids);
}
