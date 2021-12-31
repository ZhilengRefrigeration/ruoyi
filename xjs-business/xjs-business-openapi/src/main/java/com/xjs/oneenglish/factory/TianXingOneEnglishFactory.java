package com.xjs.oneenglish.factory;

import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc 一言英语工厂实现
 * @create 2021-12-31
 */
@Component
public class TianXingOneEnglishFactory implements OneEnglishFactory{

    @Override
    public ApiEnglish getOneEnglish(RequestBody requestBody) {
        return null;
    }
}
