package com.ruoyi.gateway.handler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.google.code.kaptcha.Producer;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.utils.IdUtils;
import com.ruoyi.common.core.utils.sign.Base64;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import reactor.core.publisher.Mono;

/**
 * 验证码获取
 * 
 * @author ruoyi
 */
@Component
public class ImageCodeHandler implements HandlerFunction<ServerResponse>
{
    private static final Logger log = LoggerFactory.getLogger(ImageCodeHandler.class);

    @Autowired
    private Producer producer;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {
        // 生成验证码
        String capText = producer.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String verifyCode = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = producer.createImage(capStr);
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        
        redisService.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            log.error("ImageIO write err", e);
            return Mono.error(e);
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ServerResponse.status(HttpStatus.OK)
                .body(BodyInserters.fromValue(ajax));
    }
}