package com.ruoyi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author ruoyi
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RuoYiAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n"   +
                "     .--..--..--..--..--..--.                      \n"   +
                "    .' \\  (`._   (_)     _   \\                   \n"   +
                "  .'    |  '._)         (_)  |                     \n"   +
                "  \\ _.')\\      .----..---.   /                   \n"   +
                "  |(_.'  |    /    .-\\-.  \\  |                   \n"   +
                "  \\     0|    |   ( O| O) | o|                    \n"   +
                "   |  _  |  .--.____.'._.-.  |                     \n"   +
                "   \\ (_) | o         -` .-`  |                    \n"   +
                "    |    \\   |`-._ _ _ _ _\\ /    pinnpinn.top    \n"   +
                "    \\    |   |  `. |_||_|   |                     \n"   +
                "    | o  |    \\_      \\     |     -.   .-.       \n"   +
                "    |.-.  \\     `--..-'   O |     `.`-' .'        \n"   +
                "  _.'  .' |     `-.-'      /-.__   ' .-'           \n"   +
                ".' `-.` '.|='=.='=.='=.='=|._/_ `-'.'              \n"   +
                "`-._  `.  |________/\\_____|    `-.'               \n"   +
                "   .'   ).| '=' '='\\/ '=' |                       \n"   +
                "   `._.`  '---------------'                        \n"   +
                "           //___\\   //___\\                       \n"   +
                "             ||       ||                           \n"   +
                "             ||_.-.   ||_.-.                       \n"   +
                "            (_.--__) (_.--__)                       ");
    }
}
