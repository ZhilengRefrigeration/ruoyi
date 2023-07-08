package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserBuildingRelMapper;
import com.ruoyi.system.domain.UserBuildingRel;
import com.ruoyi.system.service.IUserBuildingRelService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class UserBuildingRelServiceImpl implements IUserBuildingRelService 
{
    @Autowired
    private UserBuildingRelMapper userBuildingRelMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserBuildingRel selectUserBuildingRelById(Long id)
    {
        return userBuildingRelMapper.selectUserBuildingRelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserBuildingRel> selectUserBuildingRelList(UserBuildingRel userBuildingRel)
    {
        return userBuildingRelMapper.selectUserBuildingRelList(userBuildingRel);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserBuildingRel(UserBuildingRel userBuildingRel)
    {
        return userBuildingRelMapper.insertUserBuildingRel(userBuildingRel);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userBuildingRel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserBuildingRel(UserBuildingRel userBuildingRel)
    {
        return userBuildingRelMapper.updateUserBuildingRel(userBuildingRel);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserBuildingRelByIds(Long[] ids)
    {
        return userBuildingRelMapper.deleteUserBuildingRelByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserBuildingRelById(Long id)
    {
        return userBuildingRelMapper.deleteUserBuildingRelById(id);
    }
}
