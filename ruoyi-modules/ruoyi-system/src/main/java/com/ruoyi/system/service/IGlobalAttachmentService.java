package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.GlobalAttachment;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface IGlobalAttachmentService  extends IService<GlobalAttachment>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GlobalAttachment selectGlobalAttachmentById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GlobalAttachment> selectGlobalAttachmentList(GlobalAttachment globalAttachment);

    /**
     * 新增【请填写功能名称】
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 结果
     */
    public int insertGlobalAttachment(GlobalAttachment globalAttachment);

    /**
     * 修改【请填写功能名称】
     * 
     * @param globalAttachment 【请填写功能名称】
     * @return 结果
     */
    public int updateGlobalAttachment(GlobalAttachment globalAttachment);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGlobalAttachmentByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGlobalAttachmentById(Long id);
}
