package com.ruoyi.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  \n"   +
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
