package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.exception.CheckedException;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.utils.IdCardTool;
import io.swagger.annotations.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 吴一博
 * @date 2023年07月10日 8:50
 * @Description
 */
@RestController
@RequestMapping(value="/file")
@Api(description = "文件上传和下载")
public class WxFileController extends BaseController {

    @Value("${image.location.linux}")
    private String linuxLocation;

    @Value("${image.location.windows}")
    private String winLocation;

    @ApiOperation("文件上传")
    @PostMapping(value = "/uploadMore")
    @ResponseBody
    public TableDataInfo handleFileUpload(@RequestParam("files") MultipartFile[] fileList) {
        if(fileList==null||fileList.length==0){
            throw new CheckedException("请选择需要上传的文件！");
        }
        List<String> fileUrls=new ArrayList<>();
        for (int i = 0; i < fileList.length; i++) {
            MultipartFile file = fileList[i];
            //String filePath = winLocation;
            Date date = new Date();
            String time =  new SimpleDateFormat("yyyy-MM-dd").format(date);
            System.out.println("获取到精确到日的时间格式为"+time);
            String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
            String datePath="/"+str[0]+"/"+str[1]+"/"+str[2]+"/";
            //创建文件夹
            String filePath = linuxLocation+datePath;
            if (!file.isEmpty()) {
                try {
                    String originalFilename=file.getOriginalFilename();//原文件名称
                    String newFileName = UUID.randomUUID().toString()+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
                    String dest=filePath + newFileName;
                    File destFile = new File(filePath+newFileName);
                    //文件目录不存在需要先创建
                    if(!destFile.getParentFile().exists()){
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);
                    fileUrls.add(datePath+newFileName);
                } catch (Exception e) {
                    throw new CheckedException("第 " + i + " 个文件上传失败 ==> " + e.getMessage());
                }
            } else {
                throw new CheckedException("第 " + i + " 个文件上传失败因为文件为空 ");
            }
        }
        return getDataTable(fileUrls);
    }
    @ApiOperation("PC端专用多文件上传")
    @PostMapping(value = "/uploadMoreFiles")
    @ResponseBody
    @ApiImplicitParams(@ApiImplicitParam(name = "files",paramType = "formData",value = "图片",required = true,dataType = "file",allowMultiple=true))
    public TableDataInfo uploadMoreFiles(@RequestParam("files") MultipartFile[] fileList) {
        if(fileList==null||fileList.length==0){
            throw new CheckedException("请选择需要上传的文件！");
        }
        List<String> fileUrls=new ArrayList<>();
        for (int i = 0; i < fileList.length; i++) {
            MultipartFile file = fileList[i];
            //String filePath = winLocation;
            Date date = new Date();
            String time =  new SimpleDateFormat("yyyy-MM-dd").format(date);
            System.out.println("获取到精确到日的时间格式为"+time);
            String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
            String datePath="/"+str[0]+"/"+str[1]+"/"+str[2]+"/";
            //创建文件夹
            String filePath = linuxLocation+datePath;
            if (!file.isEmpty()) {
                try {
                    String originalFilename=file.getOriginalFilename();//原文件名称
                    String newFileName = UUID.randomUUID().toString()+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
                    String dest=filePath + newFileName;
                    File destFile = new File(filePath+newFileName);
                    //文件目录不存在需要先创建
                    if(!destFile.getParentFile().exists()){
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);
                    fileUrls.add(datePath+newFileName);
                } catch (Exception e) {
                    throw new CheckedException("第 " + i + " 个文件上传失败 ==> " + e.getMessage());
                }
            } else {
                throw new CheckedException("第 " + i + " 个文件上传失败因为文件为空 ");
            }
        }
        return getDataTable(fileUrls);
    }

    @ApiOperation(value = "身份证上传自动识别身份证数据", notes = "身份证上传自动识别身份证数据")
    @PostMapping(value = "/uploadIdCardImg")
    @ResponseBody
    public AjaxResult uploadIdCardImg(@RequestParam(value = "front：身份证含照片的一面；back：身份证带国徽的一面", required = true) String idCardSide,
                                           @RequestParam("files") MultipartFile[] fileList) {
        if(fileList==null||fileList.length==0){
            throw new CheckedException("请选择需要上传的文件！");
        }if(fileList.length!=1){
            throw new CheckedException("只能上传一张图片哦！");
        }
        Map returnMap = new HashMap();
        for (int i = 0; i < fileList.length; i++) {
            MultipartFile file = fileList[i];
            //String filePath = winLocation;
            Date date = new Date();
            String time =  new SimpleDateFormat("yyyy-MM-dd").format(date);
            System.out.println("获取到精确到日的时间格式为"+time);
            String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
            String datePath=File.separator+"idCard"+File.separator+str[0]+File.separator+str[1]+File.separator+str[2]+File.separator;
            //创建文件夹
            String filePath = linuxLocation+datePath;
            if (!file.isEmpty()) {
                try {
                    String originalFilename=file.getOriginalFilename();//原文件名称
                    String newFileName = UUID.randomUUID().toString()+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
                    String dest=filePath + newFileName;
                    File destFile = new File(dest);
                    //文件目录不存在需要先创建
                    if(!destFile.getParentFile().exists()){
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);
                    returnMap.put("relativePath",datePath+newFileName);
                    //自动识别
                    //上传成功后获取图片数据-自动识别
                    org.json.JSONObject idCardInfo = IdCardTool.automaticRecognition(idCardSide,dest);
                    returnMap.put("idCardInfo",idCardInfo);
                } catch (Exception e) {
                    throw new ServiceException("上传失败"+ JSON.toJSONString(returnMap));
                }
            } else {
                throw new ServiceException("第 " + i + " 个文件上传失败因为文件为空 ");
            }
        }
        return AjaxResult.success(returnMap);
    }

    @PostMapping(value = "/uploadIdCardImg1", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "身份证上传自动识别身份证数据", notes = "身份证上传自动识别身份证数据", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public AjaxResult uploadIdCardImg1(@RequestParam(value = "front：身份证含照片的一面；back：身份证带国徽的一面", required = true) String idCardSide,
                                        @ApiParam(value = "身份证照片", required = true) MultipartFile file) {
        Map returnMap = new HashMap();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    Date date = new Date();
                    String time =  new SimpleDateFormat("yyyy-MM-dd").format(date);
                    System.out.println("获取到精确到日的时间格式为"+time);
                    String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
                    String datePath=File.separator+"idCard"+File.separator+str[0]+File.separator+str[1]+File.separator+str[2]+File.separator;
                    // 获取图片的文件名
                    String originalFilename = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = originalFilename.substring(originalFilename.indexOf("."));
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = UUID.randomUUID().toString()+extensionName;
                    // 文件目录
                    String filePath = linuxLocation+datePath;
                    //文件路径
                    String distfilePath = filePath + newFileName;
                    File destFile = new File(distfilePath);
                    //文件目录不存在需要先创建
                    if(!destFile.getParentFile().exists()){
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);
                    returnMap.put("relativePath",datePath+newFileName);
                    //上传成功后获取图片数据-自动识别
                    JSONObject idCardInfo = IdCardTool.automaticRecognition(idCardSide,distfilePath);
                    returnMap.put("idCardInfo",idCardInfo);
                }catch (Exception e){
                    throw new CheckedException("上传失败"+JSON.toJSONString(returnMap));
                }
            }
        }
        return AjaxResult.success(returnMap);
    }
}
