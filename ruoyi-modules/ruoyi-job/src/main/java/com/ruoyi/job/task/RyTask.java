package com.ruoyi.job.task;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.job.config.BackupConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {

    @Autowired
    BackupConfig backupConfig;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }

    public void backupDataBase() {
        File file = new File(backupConfig.getBackupPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = backupConfig.getBackupPath() + "/" + LocalDate.now() + ".sql";
        /** 默认使用linux*/
        //String cmdPrefix = "/bin/sh -c ";
        String c1 = "/bin/sh";
        String c2 = "-c";
        String os_name = System.getProperty("os.name");
        // 判断是否是windows系统
        if (os_name.toLowerCase().startsWith("win")) {
            //cmdPrefix = "cmd /c ";
            c1 = "cmd";
            c2 = "/c";
        }
        String cmd = backupConfig.getMysqldumpPath()  // mysqldump的绝对路径，配置环境变量，直接写mysqldump即可
                + " -u" + backupConfig.getUsername()  // 数据库用户名
                + " -p" + backupConfig.getPassword()  // 数据库密码
                + " -P" + backupConfig.getPort()      // 数据库端口号
                + " " + backupConfig.getDbName()      // 数据库名
                + " > " + fileName;                   // 最终写入的文件路径
        try {
            System.out.println("第一个参数 " + c1);
            System.out.println("第二个参数 " + c2);
            System.out.println("具体命令 " + cmd);

            System.out.println("数据库备份START" + LocalDateTime.now());
            /**
             * exec重载方法有一个参数的，window下执行正常，linux下无法完成备份。
             * 使用多参数重载方法都可以正常备份
             */
            Process process = Runtime.getRuntime().exec(new String[]{c1, c2, cmd});
            process.waitFor();
            System.out.println("数据库备份END" + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("数据库备份失败:%s", e.getMessage()));
        }
    }
}
