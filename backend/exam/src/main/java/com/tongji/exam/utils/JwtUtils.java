package com.tongji.exam.utils;

import com.tongji.exam.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    //构建token的主题
    private static final String SUBJECT = "lsg_exam";
    //过期时间为1天
    private static final long EXPIRE = 1000 * 60 * 60 * 24;

    private static final String APP_SECRET = "";

    public static String genJsonWebToken(User user) {
        if (user == null || user.getUserId() == null || user.getUserUsername() == null || user.getUserAvatar() == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                // 下面3行设置token中间字段，携带用户的信息
                .claim("id", user.getUserId())
                .claim("username", user.getUserUsername())
                .claim("avatar", user.getUserAvatar())
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                // 生成的结果字符串太长，这里压缩下
                .compact();
    }

    /**
     * 校验token
     *
     * @param token 生成的额token
     * @return 解析出的信息
     */
    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // 篡改token会导致校验失败，走到异常分支，这里返回null
            return null;
        }
    }
}
