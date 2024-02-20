package com.ruoyi.file.service;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.file.domain.SysFile;
import com.ruoyi.file.mapper.SysFileDynamicSqlSupport;
import com.ruoyi.file.mapper.SysFileMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 文件存储记录Service业务层处理
 *
 * @author ryas
 * created on 2024-02-19
 */
@Service
public class SysFileCRUDServiceImpl implements ISysFileCRUDService {
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询文件存储记录
     *
     * @param fileId 文件存储记录主键
     * @return 文件存储记录
     */
    @Override
    public SysFile selectSysFileByFileId(String fileId) {
        Optional<SysFile> result = sysFileMapper.selectOne(dsl -> dsl.where(SysFileDynamicSqlSupport.fileId, SqlBuilder.isEqualTo(fileId)));
        return result.orElse(null);
    }

    /**
     * 查询文件存储记录列表
     *
     * @param sysFile 文件存储记录
     * @return 文件存储记录
     */
    @Override
    public List<SysFile> selectSysFileList(SysFile sysFile) {
        if (StringUtils.isNotBlank(sysFile.getSavedName()) || StringUtils.isNotBlank(sysFile.getFileId())) {
            //条件查询
            SelectStatementProvider provider = SqlBuilder.select(SysFileMapper.selectList)
                    .from(SysFileDynamicSqlSupport.sysFile)
                    .where(SysFileDynamicSqlSupport.fileId, SqlBuilder.isEqualToWhenPresent(sysFile.getFileId()))
                    .and(SysFileDynamicSqlSupport.savedName, SqlBuilder.isLikeWhenPresent(sysFile.getSavedName() == null ? null : "%" + sysFile.getSavedName() + "%"))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            return sysFileMapper.selectMany(provider);
        } else {
            //全部查询
            return sysFileMapper.select(SelectDSLCompleter.allRows());
        }
    }

    /**
     * 新增文件存储记录
     *
     * @param sysFile 文件存储记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysFile(SysFile sysFile) {
        return sysFileMapper.insertSelective(sysFile);
    }

    /**
     * 修改文件存储记录
     *
     * @param sysFile 文件存储记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysFile(SysFile sysFile) {
        return sysFileMapper.updateByPrimaryKeySelective(sysFile);
    }

    /**
     * 批量删除文件存储记录
     *
     * @param fileIds 需要删除的文件存储记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysFileByFileIds(String[] fileIds) {
        DeleteStatementProvider provider = SqlBuilder.deleteFrom(SysFileDynamicSqlSupport.sysFile)
                .where(SysFileDynamicSqlSupport.fileId, SqlBuilder.isIn(fileIds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return sysFileMapper.delete(provider);
    }

    /**
     * 删除文件存储记录信息
     *
     * @param fileId 文件存储记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysFileByFileId(String fileId) {
        return sysFileMapper.deleteByPrimaryKey(fileId);
    }
}
