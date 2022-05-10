package com.xjs.tools;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.exception.BusinessException;
import com.xjs.tools.domain.ToolMd5;
import com.xjs.tools.service.ToolMd5Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

import static com.xjs.consts.RegexConst.*;

/**
 * md5工具Controller
 *
 * @author xiejs
 * @since 2022-05-10
 */
@RestController
@RequestMapping("md5")
@Api(tags = "业务模块-md5加解密")
@Log4j2
@Validated
public class ToolMd5Controller {

    @Autowired
    private ToolMd5Service toolMd5Service;


    @GetMapping("encryption")
    @ApiOperation("加密md5")
    @Log(title = "加密md5")
    @RequiresLogin
    public AjaxResult encryption(@NotBlank(message = "参数不能为空") @Size(max = 20, message = "长度不能大于 20 字符") String str) {
        ToolMd5 md5 = toolMd5Service.encryption(str);
        return AjaxResult.success(md5);
    }


    @GetMapping("decrypt")
    @ApiOperation("解密md5")
    @Log(title = "解密md5")
    @RequiresLogin
    public AjaxResult decrypt(@NotBlank(message = "参数不能为空") @Size(max = 32, message = "长度不能大于 32 字符") String str) {
        boolean md516Regex = Pattern.matches(MD5_16_REGEX, str);
        boolean md532Regex = Pattern.matches(MD5_32_REGEX, str);

        if (!md516Regex && !md532Regex) {
            throw new BusinessException("请输入正确的md5");
        }

        String value = toolMd5Service.decrypt(str);
        if (StringUtils.isEmpty(value)) {
            throw new BusinessException("解密未成功");
        }

        return AjaxResult.success(value);
    }


}
