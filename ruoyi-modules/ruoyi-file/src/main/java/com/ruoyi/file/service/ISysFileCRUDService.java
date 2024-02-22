package com.ruoyi.file.service;

import com.ruoyi.file.domain.SysFile;

import java.util.List;

/**
 * 文件存储记录Service接口
 *
 * @author ryas
 * created on 2024-02-19
 */
public interface ISysFileCRUDService {
    /**
     * 查询文件存储记录
     *
     * @param fileId 文件存储记录主键
     * @return 文件存储记录
     */
    SysFile selectSysFileByFileId(String fileId);

    /**
     * 查询文件存储记录列表
     *
     * @param sysFile 文件存储记录
     * @return 文件存储记录集合
     */
    List<SysFile> selectSysFileList(SysFile sysFile);

    /**
     * 新增文件存储记录
     *
     * @param sysFile 文件存储记录
     * @return 结果
     */
    int insertSysFile(SysFile sysFile);

    /**
     * 修改文件存储记录
     *
     * @param sysFile 文件存储记录
     * @return 结果
     */
    int updateSysFile(SysFile sysFile);

    /**
     * 批量删除文件存储记录
     *
     * @param fileIds 需要删除的文件存储记录主键集合
     * @return 结果
     */
    int deleteSysFileByFileIds(String[] fileIds) throws Exception;

    /**
     * 删除文件存储记录信息
     *
     * @param fileId 文件存储记录主键
     * @return 结果
     */
    int deleteSysFileByFileId(String fileId) throws Exception;
}
