package com.ruoyi.system.utils.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.system.api.model.WxLoginUser;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackma on 2020/6/8.
 */
@Log4j2
public class JwtUtil {

    //密钥盐
    private static final String TOKEN_SECRET = "bastket_future";
    //签发者
    private static final String ISSURE = "BMS";
    // Token过期时间2天
    private static final Duration EXPIRE_TIME = Duration.ofDays(15);  // Duration.parse("P2D");
    //token刷新时间
    private static final Duration REFRESH_TOKEN_EXPIRE_TIME = Duration.ofDays(1);
    //app token刷新时间
    private static final Duration REFRESH_TOKEN_EXPIRE_APP_TIME = Duration.ofDays(5);

    /**
     * token放本地线程
     */
    private static ThreadLocal<String> tokenThreadLocal = new InheritableThreadLocal<>();


    public static String setToken(String token){
        tokenThreadLocal.set(token);
        return token;
    }
    public static String getToken(){
        return tokenThreadLocal.get();
    }

    /**
     * 验证token是否正确
     *
     * @param token
     * @param userId
     * @return
     */
    public static boolean verify(String token, Long userId) {
        try {
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSURE)
                    .withClaim("userId", userId)
                    .build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean verifyToken(String token, String secretKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSURE).build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 生成 access_token
     *
     * @param subject
     * @param claims
     * @return
     */
    public static String getAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, EXPIRE_TIME.toMillis());
    }

    /**
     * 生成 微信端 refresh_token
     *
     * @param subject
     * @param claims
     * @return
     */
    public static String getWxRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, REFRESH_TOKEN_EXPIRE_APP_TIME.toMillis());
    }

    /**
     * 生成 refresh_token
     *
     * @param subject
     * @param claims
     * @return
     */
    public static String getRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, REFRESH_TOKEN_EXPIRE_TIME.toMillis());
    }


    /**
     * 签发token
     *
     * @param subject
     * @param claims
     * @param ttlMillis
     * @return
     */
    public static String generateToken(String subject, Map<String, Object> claims, long ttlMillis) {
        String token = null;
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = now;
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            exp = new Date(expMillis);
        }
        String userId = String.valueOf(claims.get(Constants.JWT_USER_ID));
        String userName = String.valueOf(claims.get(Constants.JWT_USER_NAME));
        String loginName = String.valueOf(claims.get(Constants.JWT_LOGIN_NAME));
        String sessionKey = String.valueOf(claims.get(Constants.JWT_SESSION_KEY));
        String realName = String.valueOf(claims.get(Constants.JWT_REAL_NAME));
        String openId = String.valueOf(claims.get(Constants.JWT_OPEN_ID));
        /*List<String> roleCodes = (List) claims.get(Constant.JWT_ROLES_KEY);
        List<String> permissions = (List) claims.get(Constant.JWT_PERMISSIONS_KEY);
        Long userId = (Long) claims.get(Constant.JWT_USER_ID);*/
        try {
            token = JWT.create()
                    .withIssuer(ISSURE)
                    .withIssuedAt(now)
                    //.withArrayClaim(Constant.JWT_PERMISSIONS_KEY, permissions.toArray(new String[permissions.size()]))
                    //.withArrayClaim(Constant.JWT_ROLES_KEY,  roleCodes.toArray(new String[roleCodes.size()]))
                    .withClaim(Constants.JWT_USER_ID, userId)
                    .withClaim(Constants.JWT_USER_NAME,userName)
                    .withClaim(Constants.JWT_LOGIN_NAME,loginName)
                    .withClaim(Constants.JWT_SESSION_KEY,sessionKey)
                    .withClaim(Constants.JWT_REAL_NAME,realName)
                    .withClaim(Constants.JWT_OPEN_ID,openId)
                    .withSubject(subject)
                    .withExpiresAt(exp)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new ServiceException("生成token失败!" + e.getMessage());
        }
        return token;
    }

    /**
     *
     * @param request
     * @return
     */
   /* public static String getUserIdByToken(HttpServletRequest request)  {
        String token = request.getHeader("token");
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId")
                .asString();
    }*/

    /**
     * 获取token中userId
     *
     * @param token
     * @return
     */
    public static Long getUserIdByToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims=new HashMap<>();
            claims=jwt.getClaims();
            String userId=claims.get(Constants.JWT_USER_ID).asString();
            return Long.parseLong(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取token中的用户信息失败！");
        }

    }

    /**
     * 获取token中userId
     *
     * @param token
     * @return
     */
    public static WxLoginUser getLogUserByToken(String token) {
        WxLoginUser user=new WxLoginUser();
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims=new HashMap<>();
            claims=jwt.getClaims();
            String userId=claims.get(Constants.JWT_USER_ID).asString();
            String userName=claims.get(Constants.JWT_USER_NAME).asString();
            String loginName=claims.get(Constants.JWT_LOGIN_NAME).asString();
            String sessionKey=claims.get(Constants.JWT_SESSION_KEY).asString();
            String realName=claims.get(Constants.JWT_REAL_NAME).asString();
            String openId=claims.get(Constants.JWT_OPEN_ID).asString();
            user.setUserId(Long.parseLong(userId));
            user.setUserName(userName);
            user.setLoginName(loginName);
            user.setSessionKey(sessionKey);
            user.setRealName(realName);
            user.setOpenId(openId);
            log.info("user= "+ JSON.toJSONString(user) +" token= "+token);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取token中的用户信息失败！");
        }

    }

    /**
     * 获得token的过期时间
     *
     * @return
     */
    public static Long getExpireTime(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("exp").asLong();
        } catch (Exception e) {
            throw new ServiceException("获取token中的过期时间戳失败：");
        }
    }
}
