package com.xjs.common.job;

import com.xjs.copywriting.factory.CopyWritingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  获取文案定时任务
 * @create 2021-12-27
 */
@Component
public class CopyWritingJob {

    @Autowired
    private CopyWritingFactory tianXingcopyWritingFactory;
}
