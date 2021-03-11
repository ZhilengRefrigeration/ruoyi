package com.ruoyi.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言器
 * 1. 方法命名必须    配置名+RoutePredicateFactory
 */
@Component
public class MyTimeRoutePredicateFactory extends AbstractRoutePredicateFactory<MyTimeRoutePredicateFactory.Config> {


    public MyTimeRoutePredicateFactory() {
        super(MyTimeRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("datetime");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyTimeRoutePredicateFactory.Config config) {
        return (GatewayPredicate) serverWebExchange -> {
            System.out.println("自定义断言生效" + serverWebExchange.getRequest().getURI());
            LocalDate ld = LocalDate.now();
            return config.getDatetime() > ld.getYear();
        };
    }

    //配置类,接收配置文件中参数
    public static class Config {

        @NotNull
        private Integer datetime;

        public Integer getDatetime() {
            return datetime;
        }

        public void setDatetime(Integer datetime) {
            this.datetime = datetime;
        }

    }

}
