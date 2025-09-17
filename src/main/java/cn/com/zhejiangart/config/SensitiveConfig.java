package cn.com.zhejiangart.config;

import cn.com.zhejiangart.util.ConfigReader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 敏感配置类
 * 用于从外部配置文件读取敏感信息
 */
@Configuration
@ConfigurationProperties(prefix = "sensitive")
public class SensitiveConfig {
    
    /**
     * 获取数据库密码
     * @return 数据库密码
     */
    public static String getDatabasePassword() {
        return ConfigReader.getProperty("db.password", "");
    }
    
    /**
     * 获取OSS访问密钥ID
     * @return OSS访问密钥ID
     */
    public static String getOssAccessKeyId() {
        return ConfigReader.getProperty("oss.access.key.id", "");
    }
    
    /**
     * 获取OSS访问密钥Secret
     * @return OSS访问密钥Secret
     */
    public static String getOssAccessKeySecret() {
        return ConfigReader.getProperty("oss.access.key.secret", "");
    }
    
    /**
     * 获取微信公众号AppID
     * @return 微信公众号AppID
     */
    public static String getWechatMpAppId() {
        return ConfigReader.getProperty("wechat.mp.app.id", "");
    }
    
    /**
     * 获取微信公众号Secret
     * @return 微信公众号Secret
     */
    public static String getWechatMpSecret() {
        return ConfigReader.getProperty("wechat.mp.secret", "");
    }
    
    /**
     * 获取微信支付AppID
     * @return 微信支付AppID
     */
    public static String getWechatPayAppId() {
        return ConfigReader.getProperty("wechat.pay.app.id", "");
    }
    
    /**
     * 获取微信支付商户号
     * @return 微信支付商户号
     */
    public static String getWechatPayMchId() {
        return ConfigReader.getProperty("wechat.pay.mch.id", "");
    }
    
    /**
     * 获取微信支付APIv3密钥
     * @return 微信支付APIv3密钥
     */
    public static String getWechatPayApiV3Key() {
        return ConfigReader.getProperty("wechat.pay.api.v3.key", "");
    }
    
    /**
     * 获取微信支付证书序列号
     * @return 微信支付证书序列号
     */
    public static String getWechatPaySerialNo() {
        return ConfigReader.getProperty("wechat.pay.serial.no", "");
    }
    
    /**
     * 获取Redis密码
     * @return Redis密码
     */
    public static String getRedisPassword() {
        return ConfigReader.getProperty("redis.password");
    }
}