package com.ruoyi.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author ruoyi
 */
@EnableAdminServer
@SpringBootApplication
public class RuoYiMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ     \n"   +
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
