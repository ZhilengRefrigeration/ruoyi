import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class CodeGet {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/xjs-classroom?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("xiejs") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Dev\\IdeaPerject\\GitHub\\Cloud\\xjs-business\\xjs-project-classroom\\classroom-service\\classroom-service-vod\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xjs.classroom") // 设置父包名
                            .moduleName("vod") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Dev\\IdeaPerject\\GitHub\\Cloud\\xjs-business\\xjs-project-classroom\\classroom-service\\classroom-service-vod\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("subject")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("is_deleted")
                            .build()
                    ; // 设置需要生成的表名
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })

                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
