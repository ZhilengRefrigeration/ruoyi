package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.api.domain.SysDept;
import com.ruoyi.system.domain.SysDuty;
import com.ruoyi.system.domain.vo.TreeSelect;

/**
 * 职称Service接口
 * 
 * @author ruoyi
 * @date 2023-10-29
 */
public interface ISysDutyService 
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
     * 批量删除职称
     * 
     * @param ids 需要删除的职称主键集合
     * @return 结果
     */
    public int deleteSysDutyByIds(Long[] ids);

    /**
     * 删除职称信息
     * 
     * @param id 职称主键
     * @return 结果
     */
    public int deleteSysDutyById(Long id);

    /**
     * 查询职称树结构信息
     *
     * @return 职称树信息集合
     */
    public List<TreeSelect> selectDutyTreeList();

    /**
     * 构建前端所需要下拉树结构
     *
     * @param dutys 职称列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDutyTreeSelect(List<SysDuty> dutys);


    /**
     * 构建前端所需要树结构
     *
     * @param dutys 职称列表
     * @return 树结构列表
     */
    public List<SysDuty> buildDutyTree(List<SysDuty> dutys);
}
