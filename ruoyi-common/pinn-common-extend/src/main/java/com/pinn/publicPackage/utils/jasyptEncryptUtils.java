package com.pinn.publicPackage.utils;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.map.MapUtil;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * jasypt数据安全保护
 */
public class jasyptEncryptUtils {
    //根目录下
    final static String FILE_PATCH_ALL = "ruoyi-common/pinn-common-extend/src/main/resources/mybatisPlus/JasyptAll.yaml";
    final static String FILE_PATCH_DE = "ruoyi-common/pinn-common-extend/src/main/resources/mybatisPlus/JasyptDe.yaml";
    final static String CHARSET_UTF8 = "utf-8";
    final static String LINE = "\r\n";
    final static String PREFIX = "PINN@[";
    final static String SUFFIX = "]";

    public static void main(String[] args) {
//        p6spy损耗性能 生产不使用
        HashMap<String, String> dataSource = MapUtil.newHashMap();
        dataSource.put("url","jdbc:mysql://175.178.38.240:9033/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dataSource.put("p6spyurl","jdbc:p6spy:mysql://175.178.38.240:9033/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dataSource.put("username","nacos");
        dataSource.put("password","nacos2233");

        encryptionDataSource(dataSource);


//        HashMap<String, String> dataSource2 = MapUtil.newHashMap();
//        dataSource2.put("url","xvrY1Lb+8pSI993YLonk0/Zc5UJJyrCEvobsCWPKzjhFyN5+6TxFksonriogCXk3ZZKdLZ2RuGlNZ/F4ezeZtDH/gYdGpYmRmGPHJfCGDXZIHUQAdqB5xIGYxdym4UkMhvxY0zH+LfECaL4Xh4SsbYnoEe0+YlRav24x/1cNW7ZxZ+5kXLc8wRTkSTUCmEOqwBrwVII5lhNBbBva/ItLqYSFdfdyLX2g3BYBUz2iSlqH2aMH2sjUnHaaeEqpSoLq");
//        dataSource2.put("username","RZvKwBufVgR5YDlJ2ncYeWgaFIZ92BpKnepqv6EuUMFz1FY7rIyJEJyhR/NYYN7F");
//        dataSource2.put("password","ixlE6Xon2PkZGz2YWT30XodveKEhidgo3m2QWrzT9Wf9Jh5tTs/MKxEXZJPBSvij");
//
//        decryptDataSource(dataSource2);

//        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
//        standardPBEStringEncryptor.setPassword("aa78fcbe21d77af8");
//        System.out.println(standardPBEStringEncryptor.decrypt("I41o92myZEH//IF94wRL63p1fYXvq+OaltRqSCk4OhzT3Ff5G4aj4Y8URNzarpiM"));
    }


    /**
     * 打印密钥和加密串
     * @param dataSource 集合
     */
    public static void decryptDataSource(Map<String,String> dataSource){
        String key = AESMybatisPlusUtils.genOrGetKey();
        String url = decryptPwd(key,dataSource.get("url"));
        String p6spyurl = decryptPwd(key,dataSource.get("p6spyurl"));
        String username = decryptPwd(key,dataSource.get("username"));
        String password = decryptPwd(key,dataSource.get("password"));

        //保存在目录下
        FileWriter writer = new FileWriter(new File(FILE_PATCH_DE), CHARSET_UTF8);
        writer.write("key: "+ key + LINE,false);
        writer.write("url: " +url  + LINE,true);
        writer.write("p6spyurl: " + PREFIX +p6spyurl + SUFFIX + LINE,true);
        writer.write("username: " +username + LINE,true);
        writer.write("password: "  +password + LINE,true);
    }

    /**
     * 打印密钥和加密串
     * @param dataSource 集合
     */
    public static void encryptionDataSource(Map<String,String> dataSource){
        String key = AESMybatisPlusUtils.genOrGetKey();
        String url = encryptPwd(key,dataSource.get("url"));
        String p6spyurl = encryptPwd(key,dataSource.get("p6spyurl"));
        String username = encryptPwd(key,dataSource.get("username"));
        String password = encryptPwd(key,dataSource.get("password"));
        //保存在目录下
        FileWriter writer = new FileWriter(new File(FILE_PATCH_ALL), CHARSET_UTF8);
        writer.write("key: "+ key + LINE,false);
        writer.write("url: " + PREFIX +url + SUFFIX + LINE,true);
        writer.write("p6spyurl: " + PREFIX +p6spyurl + SUFFIX + LINE,true);
        writer.write("username: " + PREFIX +username + SUFFIX + LINE,true);
        writer.write("password: " + PREFIX +password + SUFFIX + LINE,true);
    }

    /**
         * Jasypt生成加密结果
         *
         * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
         * @param value    待加密值
         * @return
         */
        public static String encryptPwd(String password, String value) {
            PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
            encryptOr.setConfig(cryptOr(password));
            String result = encryptOr.encrypt(value);
            return result;
        }

        /**
         * 解密
         *
         * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
         * @param value    待解密密文
         * @return
         */
        public static String decryptPwd(String password, String value) {
            PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
            encryptOr.setConfig(cryptOr(password));
            String result = encryptOr.decrypt(value);
            return result;
        }

        /**
         * @param password salt
         * @return
         */
        public static SimpleStringPBEConfig cryptOr(String password) {
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword(password);
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setPoolSize("1");
            return config;
        }
}