package com.xjs.server;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.domain.ApiWarning;
import com.xjs.service.ApiWarningService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.xjs.consts.ApiWarnHandleConst.NO;
import static com.xjs.consts.RedisConst.WEBSOCKET;

/**
 * api预警websocket
 *
 * @author xiejs
 * @since 2022-01-13
 */
@Log4j2
@ServerEndpoint("/warning/api/{userId}")
@Component
public class WebSocketServer {
    /**
     * 之所有需要set注入，因为WebSocketServer是静态类，需要注入属性就需要用set注入
     */
    private static RedisService redisService;
    @Autowired
    public void setRedisService(RedisService redisService) {
        WebSocketServer.redisService = redisService;
    }

    private static ApiWarningService apiWarningService;
    @Autowired
    public void setApiWarningService(ApiWarningService apiWarningService) {
        WebSocketServer.apiWarningService = apiWarningService;
    }


    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
        Set<String> set = new HashSet<>();
        set.add(userId);
        redisService.setCacheSet(WEBSOCKET, set);

        long count = apiWarningService.count(new QueryWrapper<ApiWarning>().eq("handle",NO));
        JSONObject jsonData =new JSONObject();
        jsonData.put("count", count);
        jsonData.put("socketType", "apiWarning");
        jsonData.put("data", "{}");
        try {
            sendMessage(jsonData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();

            //退出移出用户
            redisService.removeSet(WEBSOCKET, this.userId);
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {


            try {
                session.getBasicRemote().sendText("回复的消息");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        redisService.removeSet(WEBSOCKET, this.userId);
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
