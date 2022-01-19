package com.xjs.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * springboot启动成功后执行
 * @author xiejs
 * @since 2022-01-19
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("-----------------启动成功！！！---------------------");
    }
}
