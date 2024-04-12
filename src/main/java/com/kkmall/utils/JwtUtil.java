package com.kkmall.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rb100820
 */
@Slf4j
public class JwtUtil {

    /**
     * 过期时间 14天
     */
    public static final long EXPIRATION = 1000 * 24 * 60 * 60 * 14;

    /**
     * 加密算法HS256 密钥
     */
    public static final String APPEARED_KEY = "KKMALL";
    public static final String USER_ID = "userId";
    public static final String ROLE = "role";
    public static final String PHONE = "phone";
    public static final String ADMIN = "10001";

    private static final byte[] HASH;

    static {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // 对输入字符串进行哈希计算
        HASH = digest.digest(APPEARED_KEY.getBytes());
    }

    private static final Key KEY = Keys.hmacShaKeyFor(HASH);

    /**
     * 新建token
     */
    public static String createToken(String userId, String role) {
        String token;
        Map<String, Object> claims = new HashMap<>() {{
            put(USER_ID, userId);
            put(ROLE, role);
        }};
        token = Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public static String createToken(String userId, String role, String phone) {
        String token;
        Map<String, Object> claims = new HashMap<>() {{
            put(USER_ID, userId);
            put(ROLE, role);
            put(PHONE, phone);
        }};
        token = Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


    /**
     * 获取userId
     */
    public static String getUserId(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get(USER_ID)
                .toString();
    }

    public static String getRole(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get(ROLE)
                .toString();
    }

    /**
     * 验证是否过期
     */
    public static boolean isExpiration(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration()
                .before(new Date());
    }
}
