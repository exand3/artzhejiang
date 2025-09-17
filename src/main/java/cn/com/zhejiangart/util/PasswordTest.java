package cn.com.zhejiangart.util;

/**
 * 密码工具测试类
 */
public class PasswordTest {
    public static void main(String[] args) {
        // 测试默认密码 123456#
        String defaultPassword = "123456#";
        String encrypted = PasswordUtil.password(defaultPassword);
        System.out.println("原始密码: " + defaultPassword);
        System.out.println("加密结果: " + encrypted);
        
        // 验证密码
        boolean verified = PasswordUtil.verify(defaultPassword, encrypted);
        System.out.println("验证结果: " + verified);
    }
}