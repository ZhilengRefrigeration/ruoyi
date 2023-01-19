package com.ruoyi.product.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.product.mapper.TbFinancialProductMapper;
import com.ruoyi.product.pojo.TbFinancialProduct;
import com.ruoyi.product.var.ConstantVars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @description: TODO
 * @author杨宗理
 * @date 2023/1/18 15:16
 */
@Component
@Slf4j
public class TaskQuartz {
    @Autowired
    ElasticsearchRestTemplate elasticsearchTemplate;
     @Autowired
      private RedisTemplate redisTemplate;
    @Autowired
    private TbFinancialProductMapper tbFinancialProductMapper;
   @Scheduled(fixedDelay = 1000*60)
    public void ProductQuartz(){
        //判断索引
        if(!elasticsearchTemplate.indexOps(TbFinancialProduct.class).exists()){
            elasticsearchTemplate.indexOps(TbFinancialProduct.class).create();
            Document mapping = elasticsearchTemplate.indexOps(TbFinancialProduct.class).createMapping();
            elasticsearchTemplate.indexOps(TbFinancialProduct.class).putMapping(mapping);

        }
       String strTime = (String) redisTemplate.opsForValue().get(ConstantVars.SYNC_PRODUCT_KEY);

        if(strTime==null){
            //全量同步
            List<TbFinancialProduct> list = tbFinancialProductMapper.selectList(new QueryWrapper<TbFinancialProduct>().lambda().orderByDesc(TbFinancialProduct::getCreateTime));
            if(list!=null && list.size()>0){
                elasticsearchTemplate.save(list);
                TbFinancialProduct product = list.get(0);
                Date updateTime = product.getUpdateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(updateTime);
                //存入redis中
                redisTemplate.opsForValue().set(ConstantVars.SYNC_PRODUCT_KEY,format);

            }
            return;

        }

        //增量同步
       //全量同步
       List<TbFinancialProduct> list = tbFinancialProductMapper.selectList(new QueryWrapper<TbFinancialProduct>().lambda().gt(TbFinancialProduct::getUpdateTime,strTime)
               .orderByDesc(TbFinancialProduct::getCreateTime));
       if(list!=null && list.size()>0){
           elasticsearchTemplate.save(list);
           TbFinancialProduct product = list.get(0);
           Date updateTime = product.getUpdateTime();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String format = sdf.format(updateTime);
           //存入redis中
           redisTemplate.opsForValue().set(ConstantVars.SYNC_PRODUCT_KEY,format);

       }
       return;


   }





}
