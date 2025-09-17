package cn.com.zhejiangart.config;

import cn.com.zhejiangart.util.ConfigReader;
import cn.com.zhejiangart.config.SensitiveConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * OSS配置类
 */
@Setter
@Getter
@Component
public class OssConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucket;
    private String domain;

    /**
     * 获取访问密钥ID
     * @return 访问密钥ID
     */
    public String getAccessKeyId() {
        if (accessKeyId == null || accessKeyId.isEmpty()) {
            return SensitiveConfig.getOssAccessKeyId();
        }
        return accessKeyId;
    }

    /**
     * 获取访问密钥Secret
     * @return 访问密钥Secret
     */
    public String getAccessKeySecret() {
        if (accessKeySecret == null || accessKeySecret.isEmpty()) {
            return SensitiveConfig.getOssAccessKeySecret();
        }
        return accessKeySecret;
    }

}