package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ShArea;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface ShAreaMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ShArea selectShAreaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param shArea 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ShArea> selectShAreaList(ShArea shArea);

    /**
     * 新增【请填写功能名称】
     * 
     * @param shArea 【请填写功能名称】
     * @return 结果
     */
    public int insertShArea(ShArea shArea);

    /**
     * 修改【请填写功能名称】
     * 
     * @param shArea 【请填写功能名称】
     * @return 结果
     */
    public int updateShArea(ShArea shArea);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteShAreaById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShAreaByIds(Long[] ids);

    List<ShArea> findMyCity(ShArea entity);
}
