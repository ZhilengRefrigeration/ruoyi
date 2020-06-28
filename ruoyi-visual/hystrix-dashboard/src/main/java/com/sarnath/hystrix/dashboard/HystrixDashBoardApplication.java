package com.sarnath.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashBoardApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(HystrixDashBoardApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  hystrix-dashboard服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "____.___._________  \n" +
                "\\__  |   |\\_   ___ \\ \n" +
                " /   |   |/    \\  \\/ \n" +
                " \\____   |\\     \\____\n" +
                " / ______| \\______  /\n" +
                " \\/               \\/ ");

    }
}
