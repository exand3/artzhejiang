package cn.com.zhejiangart.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置类
 */
@Component
@ConfigurationProperties(prefix = "wechat.pay")
@Data
public class WeChatPayConfig {
    private String appId;
    private String mchId;
    private String apiV3Key;
    private String serialNo;

    // Getters and Setters
    public String getAppId() {
        if (appId == null || appId.isEmpty()) {
            return SensitiveConfig.getWechatPayAppId();
        }
        return appId;
    }

    public String getMchId() {
        if (mchId == null || mchId.isEmpty()) {
            return SensitiveConfig.getWechatPayMchId();
        }
        return mchId;
    }

    public String getApiV3Key() {
        if (apiV3Key == null || apiV3Key.isEmpty()) {
            return SensitiveConfig.getWechatPayApiV3Key();
        }
        return apiV3Key;
    }

    public String getSerialNo() {
        if (serialNo == null || serialNo.isEmpty()) {
            return SensitiveConfig.getWechatPaySerialNo();
        }
        return serialNo;
    }
}