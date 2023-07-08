package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.WxUser;
import com.ruoyi.system.domain.enums.WxAppletsSendErrCodesEnum;
import com.ruoyi.system.domain.enums.WxAppletsTemplateIdsEnum;
import com.ruoyi.system.domain.vo.PhoneRequest;
import com.ruoyi.system.domain.vo.WxApplesRes;
import com.ruoyi.system.domain.vo.WxMssVo;
import com.ruoyi.system.domain.vo.WxPhoneNumberVo;
import com.ruoyi.system.service.ICompetitionMembersService;
import com.ruoyi.system.service.IWxUserService;
import com.ruoyi.system.service.WxAppletsService;
import com.ruoyi.system.utils.LoginUserUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴一博
 * @date 2023年07月07日 16:19
 * @Description
 */
@Log4j2
@Service
public class WxAppletsServiceImpl implements WxAppletsService {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private RedisService redisService;
    @Autowired
    private IWxUserService wxUserService;
    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.location.windows}")
    private String winLocation;
    @Value("${image.domainName}")
    private String domainName;
    @Autowired
    private ICompetitionMembersService competitionMembersService;
    @Override
    public String getAccessToken() {
        String accessToken=null;
        //如果已经获取就不需要再次请求了
        if(StringUtils.isEmpty(redisService.getCacheObject(Constants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY))) {
            //获取access_token
            String appid = Constants.WX_APPLETS_APP_ID;
            String appsecret = Constants.WX_APPLETS_APP_SERCERT;
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
            redisService.setCacheObject(Constants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY,accessToken,expires_in-100, TimeUnit.SECONDS);
            return accessToken;
        }else {
            return (String) redisService.getCacheObject(Constants.WX_APPLETS_REDIS_ACCESS_TOKEN_KEY);
        }
    }
    @Override
    public WxApplesRes pushOneUser(WxMssVo wxMssVo) {
        //如果access_token为空则从新获取
        if(StringUtils.isEmpty(wxMssVo.getAccessToken())){
            wxMssVo.setAccessToken(getAccessToken());
        }
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + wxMssVo.getAccessToken();
        //拼接推送的模版
        // Map<String, TemplateDataVo> map = new HashMap<>();
        if(wxMssVo.getTemplate_id().equals(WxAppletsTemplateIdsEnum.ACCEPT_WAR.getCode())){ //应战
//            map.put("thing1", new TemplateDataVo("admin"));
//            map.put("thing2", new TemplateDataVo(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
//            map.put("thing3", new TemplateDataVo("队长邀请成员给成员发通知"));
        }else if(wxMssVo.getTemplate_id().equals(WxAppletsTemplateIdsEnum.TREATY_WAR.getCode())){ //约战准备提醒

        }else if(wxMssVo.getTemplate_id().equals(WxAppletsTemplateIdsEnum.TREATY_WAR_SUCCESS.getCode())){ //约战成功提醒

        }
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }

        ResponseEntity<WxApplesRes> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, WxApplesRes.class);
        log.info("小程序推送结果={}", JSON.toJSONString(responseEntity.getBody()));
        responseEntity.getBody().setErrmsg(WxAppletsSendErrCodesEnum.getDescByCode(responseEntity.getBody().getErrcode()));
        return responseEntity.getBody();
    }

    @Override
    public String getPhoneNumber(PhoneRequest request) {
        String accessToken = this.getAccessToken();
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        Map param = new HashMap<>();
        param.put("code", request.getCode());
        /* param.put("access_token", accessToken);*/
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity requestEntity = new HttpEntity(param, headers);
        ResponseEntity<WxPhoneNumberVo> obj = restTemplate.exchange(url, HttpMethod.POST, requestEntity, WxPhoneNumberVo.class);
        if(obj.getBody().getErrcode()!=0){
            throw new ServiceException("手机号码获取失败");
        }
        //obj = restTemplate.postForEntity(url, param, WxPhoneNumberVo.class);
        return obj.getBody().getPhone_info().getPhoneNumber();
    }

    @Override
    public String updatePhoneNumber(PhoneRequest request) {
        LoginUser user = SecurityUtils.getLoginUser();
        //更新用户表的tel
        WxUser userInfo = new WxUser();
        userInfo.setId(user.getUserid());
        userInfo.setTelephone(this.getPhoneNumber(request));
        wxUserService.updateWxUser(userInfo);
        //todo 赛会参赛人员通过手机号码绑定userid
        competitionMembersService.bindCompetitionMembersByTel(user.getUserid(),userInfo.getTelephone());
        return  userInfo.getTelephone();
    }

    @Override
    public WxAppletsCodeVo getWxacodeunlimit(WxAppletsCodeVo wxAppletsCodeVo, String accessToken) {
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //根据APPid和密钥获取存取令牌
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + this.getAccessToken();
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
            wxAppletsCodeVo.setBase64(getBase64(file));
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
        System.out.println("获取二维码");
        return  wxAppletsCodeVo;
    }
    public static String getBase64(File file){
        String imgStr = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            fis.close();
            Base64.Encoder encoder = Base64.getMimeEncoder();
            imgStr = encoder.encodeToString(buffer);
           // imgStr = encoder.encode(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgStr;
    }
}
