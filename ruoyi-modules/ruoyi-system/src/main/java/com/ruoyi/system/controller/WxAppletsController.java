package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.service.WxAppletsService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 吴一博
 * @date 2023年07月08日 18:11
 * @Description
 */
@RestController
@RequestMapping("/wxApplets")
public class WxAppletsController {
    @Autowired
    private WxAppletsService wxAppletsService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.location.windows}")
    private String winLocation;
    @Value("${image.domainName}")
    private String domainName;

    @PostMapping("/getWxacodeunlimit")
    @ApiOperation(ApiTerminal.wxMiniProgram +"微信小程序-获取小程序码")
    @ResponseBody
    public AjaxResult getWxacodeunlimit(@RequestBody WxAppletsCodeVo wxAppletsCodeVo) throws Exception {
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //根据APPid和密钥获取存取令牌
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + wxAppletsService.getAccessToken();
            //定义生产二维码所需的参数、样式
            Map<String, Object> param = new HashMap<>();
            param.put("scene", wxAppletsCodeVo.getScene());
            param.put("page", wxAppletsCodeVo.getPage());
            param.put("width", StringUtils.isEmpty(wxAppletsCodeVo.getPage())?10:wxAppletsCodeVo.getPage());
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
            //System.out.println("协议请求头"+headers+""+requestEntity);
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            //            LOG.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            //  System.out.println("返回结果"+entity.getBody()+".."+entity);
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
            return AjaxResult.success(wxAppletsCodeVo);

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
        return AjaxResult.success(wxAppletsCodeVo);
    }

    public String getBase64(File file){
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
            BASE64Encoder encoder = new BASE64Encoder();
            imgStr = encoder.encode(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgStr;
    }
}
