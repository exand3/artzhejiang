package cn.com.zhejiangart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Properties;

/**
 * 配置读取工具类
 * 支持从外部配置文件、classpath、系统属性中读取配置
 */
public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties = new Properties();
    
    static {
        loadProperties();
    }
    
    /**
     * 加载配置文件
     */
    private static void loadProperties() {
        // 获取环境变量
        String env = System.getProperty("env", "dev"); // 默认为开发环境
        
        try {
            // 生产环境读取本地文件
            if ("prod".equals(env)) {
                String configPath = System.getProperty("config.path", "/etc/zhejiangart/config.properties");
                File configFile = new File(configPath);
                if (configFile.exists() && configFile.isFile()) {
                    try (InputStream input = new FileInputStream(configFile)) {
                        properties.load(input);
                        logger.info("已从外部文件加载配置: {}", configPath);
                    }
                } else {
                    logger.warn("生产环境配置文件不存在: {}", configPath);
                }
            } else {
                // 开发环境和其他环境从classpath加载
                Resource resource = new ClassPathResource("config-dev.properties.example");
                if (resource.exists()) {
                    try (InputStream input = resource.getInputStream()) {
                        properties.load(input);
                        logger.info("已从classpath加载配置: config.properties");
                    }
                } else {
                    logger.warn("无法在classpath中找到config.properties");
                }
            }
        } catch (IOException e) {
            logger.error("加载配置文件时发生IO异常: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 获取配置属性
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值
     */
    public static String getProperty(String key, String defaultValue) {
        // 优先从系统属性获取（环境变量或-D参数）
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }
        
        // 从配置文件获取
        value = properties.getProperty(key);
        if (value != null) {
            return value;
        }
        
        // 返回默认值
        return defaultValue;
    }
    
    /**
     * 获取配置属性（无默认值）
     * @param key 配置键
     * @return 配置值
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
}