package com.xjs.copywriting.factory.impl;

import com.xjs.common.client.api.lq.LqDogDiaryFeignClient;
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
 * @author xiejs
 * @since 2022-02-14
 */
@Component
@Log4j2
public class LqDogDiaryCopyWritingFactory implements CopyWritingFactory {

    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private LqDogDiaryFeignClient lqDogDiaryFeignClient;

    @Override
    public CopyWriting productCopyWriting(RequestBody requestBody) {
        String data = lqDogDiaryFeignClient.dogDiaryApi();
        if (StringUtils.isEmpty(data)) {
            throw new ApiException("零七-舔狗日记接口调用异常");
        }
        CopyWriting copyWriting = new CopyWriting();
        copyWriting.setContent(data);
        copyWriting.setSource("舔狗日记");
        copyWriting.setType(TianXingConst.TGRJ);

        copyWritingService.save(copyWriting);
        return copyWriting;
    }
}
