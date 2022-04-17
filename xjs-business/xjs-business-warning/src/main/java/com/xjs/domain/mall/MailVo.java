package com.xjs.domain.mall;

import com.xjs.validation.group.AddGroup;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 邮件数据传输对象
 * @author xiejs
 * @since 2022-04-14
 */
@Data
public class MailVo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 邮件接收人
     */
    @Size(min = 1, max = 40, message = "邮件接收人长度不能超过40个字符",groups = AddGroup.class)
    @NotBlank(message = "邮件接收人不能为空",groups = AddGroup.class)
    private String recipient;
    /**
     * 邮件主题
     */
    @Size(min = 1, max = 100, message = "邮件主题长度不能超过100个字符",groups = AddGroup.class)
    @NotBlank(message = "邮件主题不能为空",groups = AddGroup.class)
    private String subject;
    /**
     * 邮件内容
     */
    @NotBlank(message = "邮件内容不能为空",groups = AddGroup.class)
    @Size(min = 1, max = 50000, message = "邮件内容长度不能超过50000个字符",groups = AddGroup.class)
    private String content;

    /**
     * 文件列表
     */
    private MultipartFile[] fileList;

}
