package com.xjs;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-05-31
 */
@SpringBootApplication
@RestController
@RequestMapping("app")
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

    @Value("${server.port}")
    private Integer port;

    @GetMapping
    @SentinelResource(value = "port",
            blockHandler = "handlePort",
            blockHandlerClass = SentinelHandler.class,
            fallback = "errorPost"

    )
    public String port() {
        return port.toString();
    }


    public String handlePort(BlockException e) {
        return "异常了啊";
    }

    public String errorPost(BlockException e) {
        return "error了啊";
    }
}
