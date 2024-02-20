package com.ruoyi.file.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.file.domain.SysFile;
import com.ruoyi.file.service.ISysFileCRUDService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件存储记录Controller
 *
 * @author ryas
 * created on 2024-02-19
 */
@RestController
@RequestMapping("/FileRecord")
public class SysFileCRUDController extends BaseController {
    @Autowired
    private ISysFileCRUDService crudService;

    /**
     * 查询文件存储记录列表
     */
    @RequiresPermissions("file:FileRecord:list")
    @GetMapping("/list")
    public TableDataInfo list(SysFile sysFile) {
        startPage();
        List<SysFile> list = crudService.selectSysFileList(sysFile);
        return getDataTable(list);
    }

    /**
     * 导出文件存储记录列表
     */
    @RequiresPermissions("file:FileRecord:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFile sysFile) {
        List<SysFile> list = crudService.selectSysFileList(sysFile);
        ExcelUtil<SysFile> util = new ExcelUtil<>(SysFile.class);
        util.exportExcel(response, list, "文件存储记录数据");
    }

    /**
     * 获取文件存储记录详细信息
     */
    @RequiresPermissions("file:FileRecord:query")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") String fileId) {
        return success(crudService.selectSysFileByFileId(fileId));
    }
}
