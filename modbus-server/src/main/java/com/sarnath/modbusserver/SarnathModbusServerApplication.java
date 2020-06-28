package com.sarnath.modbusserver;

import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import com.sarnath.modbusserver.dtuutils.DTUServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCustomSwagger2
@SpringCloudApplication
public class SarnathModbusServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SarnathModbusServerApplication.class, args);


        new Thread(()->{
            DTUServer.Start();
        }).start();
        System.out.println("(♥◠‿◠)ﾉﾞ  Modbus服务端模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "____.___._________  \n" +
                "\\__  |   |\\_   ___ \\ \n" +
                " /   |   |/    \\  \\/ \n" +
                " \\____   |\\     \\____\n" +
                " / ______| \\______  /\n" +
                " \\/               \\/ ");


    }
}
