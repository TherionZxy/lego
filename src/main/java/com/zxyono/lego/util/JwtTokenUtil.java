package com.zxyono.lego.util;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.security.rsa.RSAPublicKeyImpl;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {

    private static final String SALT = "ZxyIsAGoodManAndHeLikePlaySwitchSoAreYouLikeSuperMan";//加密解密盐值

    /**
     * 生成token(请根据自身业务扩展)
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, long expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)//主题
                .compact();
    }

    /**
     * 生成token —— 微信小程序用户
     * @param user
     * @return
     */
    public static String generateToken(User user, long expirationSeconds){
        return Jwts.builder()
                .setId(user.getUserId().toString())
                .setSubject("user")
                .setExpiration(new Date(System.currentTimeMillis()  + expirationSeconds * 1000))
                .setIssuedAt(new Date())
                .setIssuer("Zxyono")
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
    }

    /**
     * 生成token —— 管理员
     * @param admin
     * @return
     */
    public static String generateToken(Admin admin, long expirationSeconds) {
        return Jwts.builder()
                .setId(admin.getAdminId().toString())
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis()  + expirationSeconds * 1000))
                .setIssuedAt(new Date())
                .setIssuer("Zxyono")
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
    }

    /**
     * 解析token,获得subject中的信息
     * @param token
     * @return
     */
    public static String parseToken(String token) {
        String subject = null;
        try {
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }

    /**
     * 获取token自定义属性
     * @param token
     * @return
     */
    public static Map<String,Object> getClaims(String token){
        Map<String,Object> claims = null;
        try {
            claims = getTokenBody(token);
        } catch (Exception e) {
            System.out.println("error");
        }

        return claims;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SALT)
                .parseClaimsJws(token)
                .getBody();
    }
}