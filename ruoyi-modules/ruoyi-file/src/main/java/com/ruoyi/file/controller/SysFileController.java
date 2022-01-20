package com.ruoyi.file.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 对访问url进行签名加密 别名 【临时安全凭证】
     * 兼容 AWS Security Token Service (STS) 的联合身份临时安全凭证 (federation token) ，更多详细信息请查阅
     * aliyun oss 实例: http://react-yuebaoxiao-pro.oss-cn-shanghai.aliyuncs.com/dev/upload/default/20210719-23d31398-4849-408d-8775-a5b668ccafc3.jpeg?Expires=1626736182&OSSAccessKeyId=LTAI4GDQSbwgmbsRxxbDXnKT&Signature=P3w3%2FIpEnZEUhYku6scOos4p54A%3D
     * minio 示例: https://yq666.bj.gov.cn/appt-file/dev/default/2021/07/19/5fe1478b-969c-4b6e-9cc0-742412dc3128.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=D99KGE6ZTQXSATTJWU24%2F20210719%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210719T112025Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=e45171d0885f006ee1de43cec9d88963e2b55c6e671740ae5695410ba16770c5
     * ---------------------------------------------------
     * 【说明】文件服务器，一般默认是不加延签参数就可以访问，要让验签看到效果，一般都需要在 对应文件服务器 bucket 上面做访问策略的配置
     */
    @ApiOperation(value = "临时安全凭证、获取加签的url", notes = "根据输入的url,获取带有临时安全凭证的url")
    @GetMapping("getPresignedUrl")
    public R<String> getPresignedUrl(
            @ApiParam("需要访问的url,字段名:fileUrl,必填;不要带有？后面的参数") @RequestParam(value = "fileUrl") String fileUrl) {
        return R.ok(sysFileService.presignedUrl(fileUrl), "获取成功");
    }
}
