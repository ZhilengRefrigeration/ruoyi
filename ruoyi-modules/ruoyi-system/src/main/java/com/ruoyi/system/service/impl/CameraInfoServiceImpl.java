package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CameraInfoMapper;
import com.ruoyi.system.domain.CameraInfo;
import com.ruoyi.system.service.ICameraInfoService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class CameraInfoServiceImpl implements ICameraInfoService 
{
    @Autowired
    private CameraInfoMapper cameraInfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CameraInfo selectCameraInfoById(Long id)
    {
        return cameraInfoMapper.selectCameraInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CameraInfo> selectCameraInfoList(CameraInfo cameraInfo)
    {
        return cameraInfoMapper.selectCameraInfoList(cameraInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCameraInfo(CameraInfo cameraInfo)
    {
        return cameraInfoMapper.insertCameraInfo(cameraInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCameraInfo(CameraInfo cameraInfo)
    {
        return cameraInfoMapper.updateCameraInfo(cameraInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCameraInfoByIds(Long[] ids)
    {
        return cameraInfoMapper.deleteCameraInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCameraInfoById(Long id)
    {
        return cameraInfoMapper.deleteCameraInfoById(id);
    }
}
