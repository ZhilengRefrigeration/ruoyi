package com.xjs.domain.mall;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private String recipient;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    /**
     * 文件列表
     */
    private MultipartFile[] fileList;

}
