package cn.com.zhejiangart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 */
public class PasswordUtil {
    
    /**
     * 密码加密算法
     * 对应PHP版本：
     * function password($value): string
     * {
     *     $value = sha1('blog_') . md5($value) . md5('_encrypt') . sha1($value);
     *     return sha1($value);
     * }
     * 
     * @param value 原始密码
     * @return 加密后的密码
     */
    public static String password(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        
        try {
            // sha1('blog_')
            String sha1Blog = sha1("blog_");
            
            // md5($value)
            String md5Value = md5(value);
            
            // md5('_encrypt')
            String md5Encrypt = md5("_encrypt");
            
            // sha1($value)
            String sha1Value = sha1(value);
            
            // 拼接所有部分
            String concatenated = sha1Blog + md5Value + md5Encrypt + sha1Value;
            
            // 最后对拼接结果进行sha1加密
            return sha1(concatenated);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用", e);
        }
    }
    
    /**
     * MD5加密
     * 
     * @param input 输入字符串
     * @return MD5加密结果
     * @throws NoSuchAlgorithmException 当MD5算法不可用时抛出
     */
    private static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    /**
     * SHA1加密
     * 
     * @param input 输入字符串
     * @return SHA1加密结果
     * @throws NoSuchAlgorithmException 当SHA1算法不可用时抛出
     */
    private static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        byte[] hash = sha1.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    /**
     * 验证密码
     * 
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        
        return encodedPassword.equals(password(rawPassword));
    }
}