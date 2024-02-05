package ryas.test;

import com.ruoyi.common.core.utils.file.FileActionHandler;
import com.ruoyi.common.core.utils.file.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Scipio
 * created on 2024/2/2
 */
public class SDOperator {

    /**
     * 清除nacos配置中心的敏感数据
     */
    public void cleanNacosConfigSqlFile(File ryConfigSqlFile) throws IOException {
        if (!ryConfigSqlFile.exists()) {
            System.err.println("文件不存在: " + ryConfigSqlFile.getAbsolutePath());
            return;
        }
        //替换敏感数据 (key: 要替换的目标字符串, value: 替换后的字符串)
        Map<String, String> replacements = Map.of();
        FileUtils.replaceInFile(ryConfigSqlFile, replacements, onReplaceHandler);

        //删除敏感数据
        String regex = "^INSERT INTO `his_config_info`[\\s\\S]*";
        FileUtils.removeLinesMatchingRegex(ryConfigSqlFile, regex, onDeleteLineHandler);
    }

    /**
     * 替换bootstrap.yml的敏感数据
     *
     * @param projectRootDir 项目根目录。
     *                       例如：new File(System.getProperty("user.dir"))
     * @param replacements 替换敏感数据 (key: 要替换的目标字符串, value: 替换后的字符串)
     */
    public void replaceBootstrapYmlFile(File projectRootDir, Map<String, String> replacements) throws IOException {
        List<File> bootstrapYmlFiles = FileUtils.findFiles(projectRootDir, "bootstrap.yml", true);
        if (bootstrapYmlFiles.isEmpty()) {
            System.err.println("未找到bootstrap.yml文件");
            return;
        }

        for (File bootstrapYmlFile : bootstrapYmlFiles) {
            System.out.println("找到bootstrap.yml文件：" + bootstrapYmlFile.getAbsolutePath());
            if (bootstrapYmlFile.getParentFile().getAbsolutePath().contains("target\\classes")) {
                System.out.println("文件被过滤，跳过\n");
                continue;
            }
            FileUtils.replaceInFile(bootstrapYmlFile, replacements, onReplaceHandler);
            System.out.println();
        }
    }

    //===============================================================================

    @SuppressWarnings("unchecked")
    private final FileActionHandler onReplaceHandler = (file, args) -> {
        Map.Entry<String, String> entry = (Map.Entry<String, String>) args[0];
        System.out.println("替换前的字符串：[" + entry.getKey() + "]，替换后的字符串：[" + entry.getValue() + "]");
    };

    private final FileActionHandler onDeleteLineHandler = (file, args) -> {
        int lineNo = (int) args[0];
        String line = (String) args[1];
        System.out.println("删除行[line:" + lineNo + "]：" + line);
    };

}
