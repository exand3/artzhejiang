package cn.com.zhejiangart.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 上传配置类
 */
@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {

    /**
     * 上传方式（local,oss,cos）
     */
    private String type;

    /**
     * 允许上传的文件类型
     */
    private String allowExt;

    /**
     * 允许上传的大小
     */
    private Long allowSize;

    /**
     * 允许上传的文件mime类型
     */
    private String allowMime;

    /**
     * 可用的上传文件方式
     */
    private String allowType;

    /**
     * 本地上传路径
     */
    private String localPath;

    /**
     * OSS配置
     */
    private OssConfig oss;

}