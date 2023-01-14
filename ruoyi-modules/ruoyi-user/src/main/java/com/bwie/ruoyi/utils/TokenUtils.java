package com.bwie.ruoyi.utils;

import io.jsonwebtoken.*;

import java.util.ArrayList;
import java.util.Date;

public class TokenUtils {

    public static int SUCCESS = 0;
    public static int ERROR_SIGN = 1;
    public static int ERROR_EXPIRE = 2;
    public static int ERROR = 3;

    private String key = "123456";
    private Integer expireTime = 60;
    private Claims claims = null;
    JwtBuilder builder = null;

    public TokenUtils() {
        builder = Jwts.builder();
    }

    public static TokenUtils token() {
        return new TokenUtils();
    }

    public TokenUtils setKey(String key) {
        this.key = key;
        return this;
    }

    public TokenUtils setExpire(Integer seconds) {
        this.expireTime = seconds;

        // 设置签发日期
        builder.setIssuedAt(new Date());
        // 设置过期时间
        long now = System.currentTimeMillis();
        long exp = now+1000*expireTime;
        builder.setExpiration( new Date( exp ) );

        return this;
    }

    public TokenUtils setClaim(String key, String value) {
        builder.claim( key, value );
        return this;
    }

    /**
     * @description 生成最终token
     * @author 军哥
     * @date 2022/6/22 15:44
     * @version 1.0
     */
    public String makeToken() {
        // 设置签名 使用HS256算法，并设置SecretKey(字符串)
        builder.signWith(SignatureAlgorithm.HS256,key);

        // 生成token
        String token = builder.compact();

        return token;
    }


    /**
     * @description 存放多个Key-Value数据
     * @author 军哥
     * @date 2022/6/22 15:47
     * @version 1.0
     */
    public TokenUtils putKeyValues(String... keyValues) {
        if(keyValues.length > 0) {
            // 添加数据
            ArrayList<String> stringPair = new ArrayList<>();
            for(String kv:keyValues) {
                // 添加键值对
                stringPair.add(kv);
                //
                if(stringPair.size()>=2) {
                    builder.claim( stringPair.get(0),stringPair.get(1) );
                    stringPair.clear();
                    continue;
                }
            }
        }

        return this;
    }

    /**
     * @description 添加键值对数据，然后生成token
     * @author 军哥
     * @date 2022/6/22 15:45
     * @version 1.0
     */
    public String createToken(String... keyValues) {

        // 存放数据
        if(keyValues.length > 0) {
            // 添加数据
            ArrayList<String> stringPair = new ArrayList<>();
            for(String kv:keyValues) {
                // 添加键值对
                stringPair.add(kv);
                //
                if(stringPair.size()>=2) {
                    builder.claim( stringPair.get(0),stringPair.get(1) );
                    stringPair.clear();
                    continue;
                }
            }
        }

        //
        // 设置签名 使用HS256算法，并设置SecretKey(字符串)
        builder.signWith(SignatureAlgorithm.HS256,key);

        // 生成token
        String token = builder.compact();

        return token;
    }

    public int parseToken(String token) {
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return TokenUtils.SUCCESS;
        }
        catch (ExpiredJwtException e) {
            System.out.println("token expired");
            claims  = e.getClaims();
            return TokenUtils.ERROR_EXPIRE;
        } catch (SignatureException e) {
            System.out.println("token signature error");
            return TokenUtils.ERROR_SIGN;
        } catch (Exception e) {
            System.out.println("token error");
            return TokenUtils.ERROR;
        }
    }

    public String getClaim(String key) {
        if(claims == null) {
            return null;
        }

        String value = (String)claims.get(key);

        return value;
    }

    /**
     * @description 测试工具类
     * @author 军哥
     * @date 2022/6/22 15:49
     * @version 1.0
     */
    public static void main(String[] args) {
        String token = TokenUtils.token()
                // 设置加密密码
                .setKey("123456")
                // 设置过期时间
                .setExpire(60 * 30)
                // 添加数据
                .setClaim("id", "" + 666)
                .setClaim("userName", "zhaoyun")
                .putKeyValues("role", "admin", "permissions", "select,delete,insert")
                // 生成token
                .makeToken();
        System.out.println("token="+token);
    }
}