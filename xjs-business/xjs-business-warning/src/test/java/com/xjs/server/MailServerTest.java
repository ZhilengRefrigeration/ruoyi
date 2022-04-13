package com.xjs.server;

import com.xjs.XjsWarningApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-04-13
 */
@SpringBootTest(classes = XjsWarningApp.class)
class MailServerTest {

    @Autowired
    private MailServer mailServer;


    @Test
    void sendSimpleMail() {
    }

    @Test
    void sendHTMLMail() {
    }

    @Test
    void sendAttachmentMail() {
    }

    @Test
    void sendInlineMail() {
    }

    @Test
    void sendTempLateMail() {
    }
}
