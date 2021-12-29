package com.xjs.copywriting.factory;

import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;

/**
 * @author xiejs
 * @desc  文案工厂，生产文案factory
 * @create 2021-12-27
 */
public interface CopyWritingFactory {

    /**
     * 统一调用第三方api接口
     *
     * @param requestBody 请求体
     * @return CopyWriting
     */
    CopyWriting productCopyWriting(RequestBody requestBody);

}
