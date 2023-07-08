package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ShAreaMapper;
import com.ruoyi.system.domain.ShArea;
import com.ruoyi.system.service.IShAreaService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class ShAreaServiceImpl implements IShAreaService 
{
    @Autowired
    private ShAreaMapper shAreaMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ShArea selectShAreaById(Long id)
    {
        return shAreaMapper.selectShAreaById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param shArea 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ShArea> selectShAreaList(ShArea shArea)
    {
        return shAreaMapper.selectShAreaList(shArea);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param shArea 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertShArea(ShArea shArea)
    {
        return shAreaMapper.insertShArea(shArea);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param shArea 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateShArea(ShArea shArea)
    {
        return shAreaMapper.updateShArea(shArea);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteShAreaByIds(Long[] ids)
    {
        return shAreaMapper.deleteShAreaByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteShAreaById(Long id)
    {
        return shAreaMapper.deleteShAreaById(id);
    }

    @Override
    public List<ShArea> findMyCity(ShArea entity) {
        return shAreaMapper.findMyCity(entity);
    }
}
