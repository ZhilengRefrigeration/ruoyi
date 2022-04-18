package com.xjs.zol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.zol.pojo.ZolNotebook;

/**
 * 爬虫数据中关村笔记本service接口
 * @author xiejs
 * @since 2022-04-18
 */
public interface ZolNotebookService extends IService<ZolNotebook> {
    /**
     * 分页查询笔记本数据
     * @param startPageMP mp封装分页数据
     * @param zolNotebook 实体
     * @return page
     */
    IPage<ZolNotebook> selectZolPhoneByPage(Page<ZolNotebook> startPageMP, ZolNotebook zolNotebook);
}
