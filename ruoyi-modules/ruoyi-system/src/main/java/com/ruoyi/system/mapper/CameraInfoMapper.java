package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CameraInfo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface CameraInfoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CameraInfo selectCameraInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CameraInfo> selectCameraInfoList(CameraInfo cameraInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertCameraInfo(CameraInfo cameraInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cameraInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateCameraInfo(CameraInfo cameraInfo);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCameraInfoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCameraInfoByIds(Long[] ids);
}
