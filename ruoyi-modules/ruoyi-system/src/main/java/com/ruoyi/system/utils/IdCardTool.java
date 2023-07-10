package com.ruoyi.system.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 身份证识别
 */
public class IdCardTool {
    //设置APPID/AK/SK
    public static final String APP_ID = "22812439";
    public static final String API_KEY = "orXDtC8LLQGBDmW3EKiE81Fv";
    public static final String SECRET_KEY = "1eTVMN545qvXe1hNryUa1ctTZqkCqMRf";

    public static void main(String[] args) throws IOException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        String idCardSide = "front";

        // 参数为本地图片路径
        String image = "H://wyb.png";
        org.json.JSONObject res = client.idcard(image, idCardSide, options);
        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
        byte[] file = Util.readFileByBytes(image);
        res = client.idcard(file, idCardSide, options);
        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 身份证正反面的自动识别
     * @param idCardSide 不传默认正面
     * @return
     * @throws IOException
     */
    public static org.json.JSONObject automaticRecognition(String idCardSide,String localPath) throws IOException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:
        //- true：检测朝向；
        //- false：不检测朝向。
        options.put("detect_direction", "true");
        //是否开启身份证风险类型(身份证复印件、临时身份证、身份证翻拍、修改过的身份证)功能，默认不开启，即：false。可选值:true-开启；false-不开启
        options.put("detect_risk", "false");
        if(StringUtils.isEmpty(idCardSide)){
            idCardSide="front";
        }
        // localPath 参数为本地图片路径
        org.json.JSONObject res = client.idcard(localPath, idCardSide, options);
        // 参数为本地图片二进制数组
/*        byte[] file = Util.readFileByBytes(image);
        res = client.idcard(file, idCardSide, options);*/
        System.out.println(JSON.toJSONString(res));
        return res;
    }
}
