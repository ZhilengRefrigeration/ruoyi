package ryas.test;

import java.io.File;
import java.util.Map;

/**
 * @author Alan Scipio
 * created on 2024/2/2
 */
public class RevertSD {

    public static void main(String[] args) {
        File projectRootDir = new File(System.getProperty("user.dir"));

        try {
            SDOperator operator = new SDOperator();

            //**************** 恢复bootstrap.yml的敏感数据 ****************
            Map<String, String> replacements = Map.of();
            operator.replaceBootstrapYmlFile(projectRootDir, replacements);

            System.out.println("恢复完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
