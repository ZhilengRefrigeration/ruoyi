package com.xjs.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.xjs.consts.EsConst;
import com.xjs.mall.search.config.ElasticsearchConfig;
import com.xjs.mall.search.service.ProductSaveService;
import com.xjs.mall.to.es.SkuEsModel;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品保存接口实现
 *
 * @author xiejs
 * @since 2022-04-06
 */
@Service
@Log4j2
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModelList) throws IOException {
        //保存到es中

        //1、给es中建立索引-product
        //   product-mapping.json文件执行

        //2、给es中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModelList) {
            IndexRequest indexRequest = new IndexRequest(EsConst.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String json = JSON.toJSONString(model);
            indexRequest.source(json, XContentType.JSON);
            bulkRequest.add(indexRequest);
        };

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, ElasticsearchConfig.COMMON_OPTIONS);

        // 如果批量错误
        boolean b = bulkResponse.hasFailures();

        List<String> collect = Arrays.stream(bulkResponse.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
        log.info("商品上架完成：{}",collect);

        return b;

    }
}
