package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.system.domain.GlobalAttachment;
import com.ruoyi.system.mapper.GlobalAttachmentMapper;
import com.ruoyi.system.service.IGlobalAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class GlobalAttachmentServiceImpl extends ServiceImpl<GlobalAttachmentMapper, GlobalAttachment> implements IGlobalAttachmentService
{
    @Autowired
    private GlobalAttachmentMapper globalAttachmentMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GlobalAttachment selectGlobalAttachmentById(Long id)
    {
        return globalAttachmentMapper.selectGlobalAttachmentById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GlobalAttachment> selectGlobalAttachmentList(GlobalAttachment globalAttachment)
    {
        return globalAttachmentMapper.selectGlobalAttachmentList(globalAttachment);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGlobalAttachment(GlobalAttachment globalAttachment)
    {
        return globalAttachmentMapper.insertGlobalAttachment(globalAttachment);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGlobalAttachment(GlobalAttachment globalAttachment)
    {
        return globalAttachmentMapper.updateGlobalAttachment(globalAttachment);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGlobalAttachmentByIds(Long[] ids)
    {
        return globalAttachmentMapper.deleteGlobalAttachmentByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGlobalAttachmentById(Long id)
    {
        return globalAttachmentMapper.deleteGlobalAttachmentById(id);
    }
}
