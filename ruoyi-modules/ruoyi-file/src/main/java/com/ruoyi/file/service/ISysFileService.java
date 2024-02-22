package com.ruoyi.file.service;

import com.ruoyi.file.domain.FileResult;
import com.ruoyi.file.domain.SysFile;
import com.ruoyi.file.mapper.SysFileDynamicSqlSupport;
import com.ruoyi.file.mapper.SysFileMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传接口
 *
 * @author ruoyi
 */
public interface ISysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 保存结果
     */
    FileResult uploadFile(MultipartFile file) throws Exception;

    /**
     * 删除文件
     *
     * @param fileIds 文件id
     */
    FileResult deleteFiles(String[] fileIds) throws Exception;

    default List<SysFile> selectFilesById(SysFileMapper sysFileMapper, String[] fileIds) {
        if (fileIds == null || fileIds.length == 0) {
            throw new IllegalArgumentException("file ids is empty");
        }
        SelectStatementProvider provider = SqlBuilder.select(SysFileMapper.selectList)
                .from(SysFileDynamicSqlSupport.sysFile)
                .where(SysFileDynamicSqlSupport.fileId, SqlBuilder.isIn(fileIds))
                .orderBy(SysFileDynamicSqlSupport.fileId)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return sysFileMapper.selectMany(provider);
    }

}
