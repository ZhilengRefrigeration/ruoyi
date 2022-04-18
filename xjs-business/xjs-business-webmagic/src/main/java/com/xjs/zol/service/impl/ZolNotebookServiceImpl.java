package com.xjs.zol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.zol.mapper.ZolNotebookMapper;
import com.xjs.zol.pojo.ZolNotebook;
import com.xjs.zol.service.ZolNotebookService;
import org.springframework.stereotype.Service;

/**
 * 爬虫数据中关村笔记本service接口实现
 * @author xiejs
 * @since 2022-04-18
 */
@Service
public class ZolNotebookServiceImpl extends ServiceImpl<ZolNotebookMapper, ZolNotebook> implements ZolNotebookService {
}
