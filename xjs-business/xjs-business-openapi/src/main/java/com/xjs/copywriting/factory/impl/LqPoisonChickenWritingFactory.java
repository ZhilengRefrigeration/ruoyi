package com.xjs.copywriting.factory.impl;

import com.xjs.common.client.api.lq.LqPoisonChickenFeignClient;
import com.xjs.consts.TianXingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 零七平台 毒鸡汤api工厂实现
 * @author xiejs
 * @since 2022-02-14
 */
@Component
@Log4j2
public class LqPoisonChickenWritingFactory implements CopyWritingFactory {

    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private LqPoisonChickenFeignClient lqPoisonChickenFeignClient;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        String data = lqPoisonChickenFeignClient.poisonChickenApi();
        if (StringUtils.isEmpty(data)) {
            throw new ApiException("零七-毒鸡汤接口调用异常");
        }
        CopyWriting copyWriting = new CopyWriting();
        copyWriting.setContent(data);
        copyWriting.setSource("毒鸡汤");
        copyWriting.setType(TianXingConst.DJT);
        copyWritingService.save(copyWriting);
        return copyWriting;
    }
}
