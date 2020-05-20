package com.ruoyi.common.security.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * OAuth2 自定义异常处理
 * 
 * @author ruoyi
 */
public class RyWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception>
{
    private final Logger logger = LoggerFactory.getLogger(RyWebResponseExceptionTranslator.class);

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e)
    {
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        logger.error("RyWebResponseExceptionTranslator.status:{},oAuth2ErrorCode:{},message:{}",
                oAuth2Exception.getHttpErrorCode(), oAuth2Exception.getOAuth2ErrorCode(), oAuth2Exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(oAuth2Exception);
    }
}
