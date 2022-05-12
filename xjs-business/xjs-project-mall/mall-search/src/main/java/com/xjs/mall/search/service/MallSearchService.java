package com.xjs.mall.search.service;

import com.xjs.mall.search.vo.SearchParam;
import com.xjs.mall.search.vo.SearchResult;

/**
 * @author xiejs
 * @since 2022-05-11
 */
public interface MallSearchService {

    /**
     *
     * @param searchParam 检索的所有参数
     * @return obj
     */
    SearchResult search(SearchParam searchParam);

}
