package com.ruoyi.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author ruoyi
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RuoYiFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n"   +
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
