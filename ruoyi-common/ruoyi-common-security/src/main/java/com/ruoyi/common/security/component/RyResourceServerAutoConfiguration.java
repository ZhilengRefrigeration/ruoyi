package com.ruoyi.common.security.component;

import java.io.IOException;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@ConfigurationPropertiesScan
@ComponentScan("com.ruoyi.common.security")
public class RyResourceServerAutoConfiguration
{
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler()
        {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException
            {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value())
                {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }
}
