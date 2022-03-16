package com.ruoyi.file.controller;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.file.utils.OssClient;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ruoyi.file.utils.OssClient.*;

/**
 * oss服务控制器
 *
 * @author xiejs
 * @since 2022-03-15
 */
@RestController
@RequestMapping("oss")
@Api(tags = "OSS管理")
@Log4j2
public class OssController {

    @Autowired
    OssClient ossClient;


    @GetMapping("/policy")
    public R policy() {
        String host = HTTPS + bucketName + DOT + endpoint;
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Map<String, String> respMap = null;
        try {
            //10分钟过期
            long expireTime = 600;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.getOssClient().generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.getOssClient().calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<>();
            respMap.put("accessid", keyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return R.ok(respMap);
    }

}
