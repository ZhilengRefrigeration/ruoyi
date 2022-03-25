package com.pinn.publicPackage.utils;

import ch.qos.logback.core.util.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.AES;

import java.io.File;
import java.net.URL;
import java.util.Map;

public class AESMybatisPlusUtils {

    //根目录下
    final static String FILE_PATCH_KEY = "ruoyi-common/pinn-common-extend/src/main/resources/mybatisPlus/EncryptKEY.properts";
    final static String FILE_PATCH_URL = "ruoyi-common/pinn-common-extend/src/main/resources/mybatisPlus/EncryptURL.yaml";
    final static String FILE_PATCH_ALL = "ruoyi-common/pinn-common-extend/src/main/resources/mybatisPlus/EncryptAll.yaml";
    final static String CHARSET_UTF8 = "utf-8";
    final static String LINE = "\r\n";

    /**
     * 打印密钥和加密串
     * @param dataSource 集合
     */
    public static void encryptionDataSource(Map<String,String> dataSource){
        String url = encryptionData(dataSource.get("url"));
        String username = encryptionData(dataSource.get("username"));
        String password = encryptionData(dataSource.get("password"));

        //保存在目录下
        FileWriter writer = new FileWriter(new File(FILE_PATCH_ALL), CHARSET_UTF8);
        writer.write("key: "+ genOrGetKey() + LINE,false);
        writer.write("url: "+url + LINE,true);
        writer.write("username: " +username + LINE,true);
        writer.write("password: " +password+ LINE,true);
    }


    /**
     * 打印密钥和加密串
     * @param url dataSourceUrl
     */
    public static void encryptionUrl(String url){
        String s = encryptionData(url);

        //保存在目录下
        FileWriter writer = new FileWriter(new File(FILE_PATCH_URL), CHARSET_UTF8);
        File write = writer.write("url: "+s);
        //查看路径
        //FileUtil.fileToURL(write);
    }

    /**
     * 获取密钥生成加密串
     * @param data
     * @return
     */
    private static String encryptionData(String data){
        String key = genOrGetKey();
        // 随机密钥加密
        String result = AES.encrypt(data, key);
        return "mpw:"+result;
    }

    /**
     * 生成密钥或者获取密钥
     */
    public static String genOrGetKey(){
        String randomKey = "";
        FileReader fileReader = new FileReader(new File(FILE_PATCH_KEY));
        String result = fileReader.readString();
        if (ObjectUtil.isNull(result) || ObjectUtil.equals("",result)){
            // 生成 16 位随机 AES 密钥
            randomKey = AES.generateRandomKey();
            FileWriter writer = new FileWriter(new File(FILE_PATCH_KEY), CHARSET_UTF8);
            writer.write(randomKey);
        }else {
            randomKey = result;
        }
        return randomKey;
    }


}
