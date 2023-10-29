package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysDuty;

/**
 * 职称Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-29
 */
public interface SysDutyMapper 
{
    /**
     * 查询职称
     * 
     * @param id 职称主键
     * @return 职称
     */
    public SysDuty selectSysDutyById(Long id);

    /**
     * 查询职称列表
     * 
     * @param sysDuty 职称
     * @return 职称集合
     */
    public List<SysDuty> selectSysDutyList(SysDuty sysDuty);

    /**
     * 新增职称
     * 
     * @param sysDuty 职称
     * @return 结果
     */
    public int insertSysDuty(SysDuty sysDuty);

    /**
     * 修改职称
     * 
     * @param sysDuty 职称
     * @return 结果
     */
    public int updateSysDuty(SysDuty sysDuty);

    /**
     * 删除职称
     * 
     * @param id 职称主键
     * @return 结果
     */
    public int deleteSysDutyById(Long id);

    /**
     * 批量删除职称
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDutyByIds(Long[] ids);
}
