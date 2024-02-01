package com.ruoyi.common.datasource.mybatis.gen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis代码生成辅助工具
 *
 * @author Alan Scipio
 * created on 2024/1/3
 * @see <a href="https://mybatis.org/generator/">官方文档</a>
 */
public class MyBatisGenerationHelper {

    /**
     * 执行生成
     *
     * @param configFilePath [可选]配置文件路径，默认为 /generatorConfig.xml
     */
    public static void generate(String configFilePath) {
        if (configFilePath == null || configFilePath.isEmpty()) {
            configFilePath = "/generatorConfig.xml";
        }
        try (InputStream in = MyBatisGenerationHelper.class.getResourceAsStream(configFilePath)) {
            if (in == null) {
                throw new FileNotFoundException(configFilePath);
            }

            List<String> warnings = new ArrayList<>();

            ConfigurationParser cp = new ConfigurationParser(warnings);
            System.out.println("Start to parse config file...");
            Configuration config = cp.parseConfiguration(in);
            System.out.println("Config file parsed");

            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            System.out.println("Start to generate code files...");
            myBatisGenerator.generate(null);
            System.out.println("Generation finished");

            for (String warning : warnings) {
                System.out.println("[warning] " + warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generate() {
        generate(null);
    }

}
