package ryas.test;

import java.io.File;
import java.util.Map;

/**
 * 清除敏感数据
 *
 * @author Alan Scipio
 * created on 2024/2/2
 */
public class CleanSD {

    public static void main(String[] args) {

        File ryConfigSqlFile = new File(System.getProperty("user.dir") + File.separator + "sql" + File.separator + "ry-config-ryas.sql");
//        File ryConfigSqlFile = new File("D:\\ry-config.sql"); // for test

        File projectRootDir = new File(System.getProperty("user.dir"));

        try {
            SDOperator operator = new SDOperator();

            //**************** 清除nacos配置中心的敏感数据 ****************
            operator.cleanNacosConfigSqlFile(ryConfigSqlFile);

            //**************** 清除bootstrap.yml的敏感数据 ****************
            //替换敏感数据 (key: 要替换的目标字符串, value: 替换后的字符串)
            Map<String, String> replacements = Map.of();
            operator.replaceBootstrapYmlFile(projectRootDir, replacements);

            System.out.println("清除完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
