package com.xjs.oneenglish.factory;

import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;

/**
 * @author xiejs
 * @desc  英语一言工厂
 * @create 2021-12-31
 */
public interface OneEnglishFactory {

    /**
     * 一言英语 工厂方法
     * @param requestBody 请求参数
     * @return ApiEnglish
     */
    ApiEnglish getOneEnglish(RequestBody requestBody);

}
