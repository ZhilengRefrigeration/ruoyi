package com.xjs.weixin.service.impl;

import com.xjs.weixin.service.WeiXinLinkService;
import com.xjs.weixin.task.WeiXinLinkTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信文章链接service接口实现
 *
 * @author xiejs
 * @since 2022-03-17
 */
@Service
public class WeiXinLinkServiceImpl implements WeiXinLinkService {

    @Autowired
    private WeiXinLinkTask weiXinLinkTask;

    @Override
    public Boolean getPicture(String link) {
        Long count = weiXinLinkTask.reptileWeiXinLink(link);
        return count != 0L;
    }
}
