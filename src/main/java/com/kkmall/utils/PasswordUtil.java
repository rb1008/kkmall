package com.kkmall.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * PasswordUtil
 *
 * @author rb100820
 */
@Slf4j
public class PasswordUtil {


    private static final int SALT_LENGTH = 16;

    private static final String salt = "password";

    /**
     * 使用盐对密码加密
     *
     * @param password 未加密密码
     * @return 加密后密码
     * @throws NoSuchAlgorithmException 当请求特定的加密算法但在环境中不可用时，会抛出此异常。
     */
    public static String encode(String password) throws NoSuchAlgorithmException {
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(saltBytes);
        byte[] passwordBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(passwordBytes);
    }

    /**
     * 比较加密后的密码和未加密的密码
     *
     * @param rowPassword 加密后的密码
     * @param password    未加密密码
     * @return 是否匹配
     * @throws NoSuchAlgorithmException 当请求特定的加密算法但在环境中不可用时，会抛出此异常。
     */
    public static boolean contain(String rowPassword, String password)
            throws NoSuchAlgorithmException {
        String encodePassword = encode(password);
        return rowPassword.equals(encodePassword);
    }
}
