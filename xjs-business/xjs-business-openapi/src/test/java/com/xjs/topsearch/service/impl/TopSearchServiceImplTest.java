package com.xjs.topsearch.service.impl;

import com.xjs.XjsOpenApiApp;
import com.xjs.topsearch.service.TopSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-01-22
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class TopSearchServiceImplTest {

    @Autowired
    private TopSearchService topSearchService;

    @Test
    void getHistoryTopSearchByDate() {

        Map<String, List> date = topSearchService.getHistoryTopSearchByDate("2022-01-22 20:20:20");

        List allnetworkList = date.get("allnetworkList");

    }
}