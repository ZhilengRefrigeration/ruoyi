package com.xjs.domain.mall;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 邮箱发送实体
 *
 * @author xiejs
 * @since 2022-04-13
 */
@Data
public class MailBean implements Serializable {

    private static final long serialVersionUID = -2116367492649751914L;

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
     * 用户名称
     */
    private String userName;

    /**
     * 抄送人
     */
    private List<String> cc;

    /**
     * 附件地址
     */
    private String absolutePath;

    /**
     * 文件列表
     */
    private MultipartFile[] fileList;



    /**
     * 邮件发送类型
     */
    private MailType mailType;

    /**
     * 内部类-邮件发送类型
     */
    public enum MailType {
        SIMPLE(1, "文本邮件"),
        HTML(2, "HTML邮件"),
        ATTACHMENT(3, "附件邮件"),
        INLINE(4, "静态资源邮件"),
        TEMPLATE(5, "模板邮件");


        private final int code;
        private final String msg;

        MailType(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

    }

}


