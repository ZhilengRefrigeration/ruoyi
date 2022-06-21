package com.xjs.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xjs.config.filter.DesensitizedValueFilter;
import com.xjs.config.filter.IgnoreNullValueFilter;
import com.xjs.config.filter.SnowflakeValueFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局序列化处理配置
 *
 * @author xiejs
 * @since 2021-12-26
 */
@Configuration
@Log4j2
public class JsonConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        List<SerializerFeature> list = new ArrayList<>();
        list.add(SerializerFeature.PrettyFormat);
        list.add(SerializerFeature.WriteMapNullValue);
        list.add(SerializerFeature.WriteNullStringAsEmpty);
        list.add(SerializerFeature.WriteNullListAsEmpty);
        list.add(SerializerFeature.QuoteFieldNames);
        list.add(SerializerFeature.WriteDateUseDateFormat);
        list.add(SerializerFeature.DisableCircularReferenceDetect);
        list.add(SerializerFeature.WriteBigDecimalAsPlain);

        //返回枚举类型为枚举toString  mp通用枚举用到
        list.add(SerializerFeature.WriteEnumUsingToString);

        fastJsonConfig.setSerializerFeatures(list.toArray(new SerializerFeature[list.size()]));


        //解决远程调用  ---（Content-Type cannot contain wildcard type '*'）报错
        fastConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));

        fastJsonConfig.setSerializeFilters(new DesensitizedValueFilter(), new IgnoreNullValueFilter(),new SnowflakeValueFilter());

        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }

}
