package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.vo.TreeSelect;
import com.ruoyi.system.mapper.SysDutyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SysDuty;
import com.ruoyi.system.service.ISysDutyService;

/**
 * 职称Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-29
 */
@Service
public class SysDutyServiceImpl implements ISysDutyService 
{
    @Autowired
    private SysDutyMapper sysDutyMapper;

    /**
     * 查询职称
     * 
     * @param id 职称主键
     * @return 职称
     */
    @Override
    public SysDuty selectSysDutyById(Long id)
    {
        return sysDutyMapper.selectSysDutyById(id);
    }

    /**
     * 查询职称列表
     * 
     * @param sysDuty 职称
     * @return 职称
     */
    @Override
    public List<SysDuty> selectSysDutyList(SysDuty sysDuty)
    {
        return sysDutyMapper.selectSysDutyList(sysDuty);
    }

    /**
     * 新增职称
     * 
     * @param sysDuty 职称
     * @return 结果
     */
    @Override
    public int insertSysDuty(SysDuty sysDuty)
    {
        return sysDutyMapper.insertSysDuty(sysDuty);
    }

    /**
     * 修改职称
     * 
     * @param sysDuty 职称
     * @return 结果
     */
    @Override
    public int updateSysDuty(SysDuty sysDuty)
    {
        return sysDutyMapper.updateSysDuty(sysDuty);
    }

    /**
     * 批量删除职称
     * 
     * @param ids 需要删除的职称主键
     * @return 结果
     */
    @Override
    public int deleteSysDutyByIds(Long[] ids)
    {
        return sysDutyMapper.deleteSysDutyByIds(ids);
    }

    /**
     * 删除职称信息
     * 
     * @param id 职称主键
     * @return 结果
     */
    @Override
    public int deleteSysDutyById(Long id)
    {
        return sysDutyMapper.deleteSysDutyById(id);
    }

    /**
     * 查询职称树结构信息
     *
     * @return 职称树信息集合
     */
    @Override
    public List<TreeSelect> selectDutyTreeList() {
        List<SysDuty> dutys = selectSysDutyList(new SysDuty());
        return buildDutyTreeSelect(dutys);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param dutys 职称列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDutyTreeSelect(List<SysDuty> dutys)
    {
        List<SysDuty> deptTrees = buildDutyTree(dutys);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param dutys 职称列表
     * @return 树结构列表
     */
    @Override
    public List<SysDuty> buildDutyTree(List<SysDuty> dutys)
    {
        List<SysDuty> returnList = new ArrayList<>();
        List<Long> tempList = dutys.stream().map(SysDuty::getId).collect(Collectors.toList());
        for (SysDuty duty : dutys)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(duty.getParentId()))
            {
                recursionFn(dutys, duty);
                returnList.add(duty);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = dutys;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDuty> list, SysDuty t)
    {
        // 得到子节点列表
        List<SysDuty> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDuty tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDuty> getChildList(List<SysDuty> list, SysDuty t)
    {
        List<SysDuty> tlist = new ArrayList<>();
        Iterator<SysDuty> it = list.iterator();
        while (it.hasNext())
        {
            SysDuty n = it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDuty> list, SysDuty t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
