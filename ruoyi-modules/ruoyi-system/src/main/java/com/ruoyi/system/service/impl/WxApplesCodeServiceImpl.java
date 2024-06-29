package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.service.WxApplesCodeService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴一博
 * @date 2022年11月17日 17:12
 * @Description
 */
@Log4j2
@Service
public class WxApplesCodeServiceImpl implements WxApplesCodeService {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private RedisService redisService;

    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.domainName}")
    private String domainName;
    @Override
    public String getWxApplesAccessToken() {
        String accessToken=null;
        //如果已经获取就不需要再次请求了
        if(ObjectUtils.isEmpty(redisService.getCacheObject(CacheConstants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY))) {
            //获取access_token
            String appid = CacheConstants.WX_APPLETS_APP_ID;
            String appsecret = CacheConstants.WX_APPLETS_APP_SERCERT;
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                    "&appid=" + appid + "&secret=" + appsecret;
            if (restTemplate == null) {
                restTemplate = new RestTemplate();
            }
            String json = restTemplate.getForObject(url, String.class);
            JSONObject myJson = JSONObject.parseObject(json);
            log.info("获取AccessToken={}", JSON.toJSONString(myJson));
            //保存到缓存
            accessToken = myJson.get("access_token").toString();
            Long expires_in=myJson.getLong("expires_in");
            redisService.setCacheObject(CacheConstants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY,accessToken,expires_in-100, TimeUnit.SECONDS);
            return accessToken;
        }else {
            return (String) redisService.getCacheObject(CacheConstants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY);
        }
    }

    @Override
    public WxAppletsCodeVo genWxApplesAqrCode(WxAppletsCodeVo wxAppletsCodeVo) {
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //根据APPid和密钥获取存取令牌
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + this.getWxApplesAccessToken();
            //定义生产二维码所需的参数、样式
            Map<String, Object> param = new HashMap<>();
            param.put("scene", wxAppletsCodeVo.getScene());
            param.put("page", wxAppletsCodeVo.getPage());
            param.put("width", org.apache.commons.lang3.StringUtils.isEmpty(wxAppletsCodeVo.getPage())?10:wxAppletsCodeVo.getPage());
            param.put("auto_color", wxAppletsCodeVo.getAutoColor()==null?false:wxAppletsCodeVo.getAutoColor());
            param.put("is_hyaline",wxAppletsCodeVo.getIsHyaline()==null?false:wxAppletsCodeVo.getIsHyaline());
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            System.out.println(param+"调用微信URL传参");
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            // System.out.println("协议请求头"+headers+""+requestEntity);
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            //            LOG.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            // System.out.println("返回结果"+entity.getBody()+".."+entity);
            byte[] result = entity.getBody();
//            LOG.info(Base64.encodeBase64String(result));
            //   System.out.println("不知道是什么："+Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);
//            生成随机数命名图片
            String filename = UUID.randomUUID().toString();
            //     System.out.println(filename);
            Date date = new Date();
            String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
            String datePath="/"+str[0]+"/"+str[1]+"/"+str[2]+"/";
            //创建文件夹
            String xdpath = datePath+filename+".png";
            String filePath = linuxLocation+datePath+filename+".png";
//           服务器存放位置
            File file = new File(filePath);
            //文件目录不存在需要先创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            wxAppletsCodeVo.setBytesBase64(result);
            wxAppletsCodeVo.setCodeImgUrl(domainName+xdpath);
            return  wxAppletsCodeVo;

        } catch (Exception e) {
//            LOG.error("调用小程序生成微信永久小程序码URL接口异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  wxAppletsCodeVo;
    }

    @Override
    public WxAppletsCodeVo genWxApplesAqrCodeForPc(WxAppletsCodeVo wxAppletsCodeVo) {
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //根据APPid和密钥获取存取令牌
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + this.getWxApplesAccessToken();
            //定义生产二维码所需的参数、样式
            Map<String, Object> param = new HashMap<>();
            param.put("scene", wxAppletsCodeVo.getScene());
            param.put("page", wxAppletsCodeVo.getPage());
            param.put("check_path",wxAppletsCodeVo.getCheckPath()==null?Boolean.FALSE:wxAppletsCodeVo.getCheckPath());
            if(wxAppletsCodeVo.getEnvVersion()!=null&&!StringUtils.isEmpty(wxAppletsCodeVo.getEnvVersion())){
                param.put("env_version",wxAppletsCodeVo.getEnvVersion());
            }
            param.put("width", org.apache.commons.lang3.StringUtils.isEmpty(wxAppletsCodeVo.getPage())?10:wxAppletsCodeVo.getPage());
            param.put("auto_color", wxAppletsCodeVo.getAutoColor()==null?false:wxAppletsCodeVo.getAutoColor());
            param.put("is_hyaline",wxAppletsCodeVo.getIsHyaline()==null?false:wxAppletsCodeVo.getIsHyaline());
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            // System.out.println("协议请求头"+headers+""+requestEntity);
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            //            LOG.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            // System.out.println("返回结果"+entity.getBody()+".."+entity);
            byte[] result = entity.getBody();
//            inputStream = new ByteArrayInputStream(result);
////            生成随机数命名图片
//            String filename = UUID.randomUUID().toString();
//            //     System.out.println(filename);
//            Date date = new Date();
//            String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
//            String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
//            String datePath="/"+str[0]+"/"+str[1]+"/"+str[2]+"/";
//            //创建文件夹
//            String xdpath = datePath+filename+".png";
//            String filePath = linuxLocation+datePath+filename+".png";
////           服务器存放位置
//            File file = new File(filePath);
//            //文件目录不存在需要先创建
//            if(!file.getParentFile().exists()){
//                file.getParentFile().mkdirs();
//            }
//            outputStream = new FileOutputStream(file);
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
//                outputStream.write(buf, 0, len);
//            }
//            outputStream.flush();

            wxAppletsCodeVo.setBytesBase64(result);
//            wxAppletsCodeVo.setCodeImgUrl(domainName+xdpath);
            wxAppletsCodeVo.setBase64(new String(Objects.requireNonNull(Base64.encodeBase64(result))));
            return  wxAppletsCodeVo;
        } catch (Exception e) {
            log.error("调用小程序生成微信永久小程序码URL接口异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  wxAppletsCodeVo;
    }
}
