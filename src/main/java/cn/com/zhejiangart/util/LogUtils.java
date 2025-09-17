package cn.com.zhejiangart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * 提供统一的日志记录方法
 */
public class LogUtils {
    
    /**
     * 获取业务日志记录器
     */
    public static Logger getBusinessLogger() {
        return LoggerFactory.getLogger("business");
    }
    
    /**
     * 获取系统日志记录器
     */
    public static Logger getSystemLogger() {
        return LoggerFactory.getLogger("system");
    }
    
    /**
     * 获取SQL日志记录器
     */
    public static Logger getSqlLogger() {
        return LoggerFactory.getLogger("sql");
    }
    
    /**
     * 获取拦截器日志记录器
     */
    public static Logger getInterceptorLogger() {
        return LoggerFactory.getLogger("interceptor");
    }
    
    /**
     * 记录业务操作日志
     */
    public static void logBusinessOperation(String operation, String userId, String details) {
        Logger logger = getBusinessLogger();
        logger.info("业务操作 - 操作: {}, 用户ID: {}, 详情: {}", operation, userId, details);
    }
    
    /**
     * 记录业务操作日志（带异常）
     */
    public static void logBusinessOperation(String operation, String userId, String details, Exception e) {
        Logger logger = getBusinessLogger();
        logger.error("业务操作异常 - 操作: {}, 用户ID: {}, 详情: {}, 异常: {}", operation, userId, details, e.getMessage(), e);
    }
    
    /**
     * 记录系统操作日志
     */
    public static void logSystemOperation(String operation, String details) {
        Logger logger = getSystemLogger();
        logger.info("系统操作 - 操作: {}, 详情: {}", operation, details);
    }
    
    /**
     * 记录系统操作日志（带异常）
     */
    public static void logSystemOperation(String operation, String details, Exception e) {
        Logger logger = getSystemLogger();
        logger.error("系统操作异常 - 操作: {}, 详情: {}, 异常: {}", operation, details, e.getMessage(), e);
    }
    
    /**
     * 记录SQL执行日志
     */
    public static void logSqlExecution(String sql, Object params, long executionTime) {
        Logger logger = getSqlLogger();
        logger.debug("SQL执行 - SQL: {}, 参数: {}, 执行时间: {}ms", sql, params, executionTime);
    }
    
    /**
     * 记录SQL执行异常
     */
    public static void logSqlException(String sql, Object params, Exception e) {
        Logger logger = getSqlLogger();
        logger.error("SQL执行异常 - SQL: {}, 参数: {}, 异常: {}", sql, params, e.getMessage(), e);
    }
    
    /**
     * 记录拦截器日志
     */
    public static void logInterceptor(String operation, String requestURI, String clientIP, String details) {
        Logger logger = getInterceptorLogger();
        logger.info("拦截器操作 - 操作: {}, URI: {}, IP: {}, 详情: {}", operation, requestURI, clientIP, details);
    }
    
    /**
     * 记录拦截器异常
     */
    public static void logInterceptorException(String operation, String requestURI, String clientIP, Exception e) {
        Logger logger = getInterceptorLogger();
        logger.error("拦截器异常 - 操作: {}, URI: {}, IP: {}, 异常: {}", operation, requestURI, clientIP, e.getMessage(), e);
    }
    
    /**
     * 记录用户登录日志
     */
    public static void logUserLogin(String userId, String username, String clientIP, boolean success) {
        Logger logger = getBusinessLogger();
        if (success) {
            logger.info("用户登录成功 - 用户ID: {}, 用户名: {}, IP: {}", userId, username, clientIP);
        } else {
            logger.warn("用户登录失败 - 用户ID: {}, 用户名: {}, IP: {}", userId, username, clientIP);
        }
    }
    
    /**
     * 记录用户登出日志
     */
    public static void logUserLogout(String userId, String username, String clientIP) {
        Logger logger = getBusinessLogger();
        logger.info("用户登出 - 用户ID: {}, 用户名: {}, IP: {}", userId, username, clientIP);
    }
    
    /**
     * 记录API访问日志
     */
    public static void logApiAccess(String method, String requestURI, String userId, String clientIP, int statusCode, long responseTime) {
        Logger logger = getBusinessLogger();
        logger.info("API访问 - 方法: {}, URI: {}, 用户ID: {}, IP: {}, 状态码: {}, 响应时间: {}ms", 
                   method, requestURI, userId, clientIP, statusCode, responseTime);
    }
    
    /**
     * 记录API异常日志
     */
    public static void logApiException(String method, String requestURI, String userId, String clientIP, Exception e) {
        Logger logger = getBusinessLogger();
        logger.error("API异常 - 方法: {}, URI: {}, 用户ID: {}, IP: {}, 异常: {}", 
                    method, requestURI, userId, clientIP, e.getMessage(), e);
    }
}
