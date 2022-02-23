package com.xjs.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局序列化处理配置
 * @author xiejs
 * @since  2021-12-26
 */
@Configuration
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
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;

        //解决远程调用  ---（Content-Type cannot contain wildcard type '*'）报错
        fastConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));

        //解决mp雪花算法前端精度丢失
        fastJsonConfig.setSerializeFilters(new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if ((StringUtils.endsWith(name, "Id") || StringUtils.equals(name,"id")) && value != null
                        && value.getClass() == Long.class) {
                    return String.valueOf(value);
                }
                return value;
            }
        });
        return new HttpMessageConverters(converter);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }

}
