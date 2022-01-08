package com.xjs.aword.factory;

import com.xjs.aword.domain.ApiAWord;
import com.xjs.aword.domain.RequestBody;

/**
 * 每日一句工厂接口类
 * @author xiejs
 * @since 2022-01-08
 */
public interface AWordFactory {

    /**
     * 调用每日一句接口生产数据
     * @param requestBody 请求参数
     * @return ApiAWord
     */
    ApiAWord productApiAWord(RequestBody requestBody);

}
