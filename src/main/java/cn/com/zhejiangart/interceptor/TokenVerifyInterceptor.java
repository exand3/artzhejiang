package cn.com.zhejiangart.interceptor;



import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.com.zhejiangart.mapper.UserMapper;
import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.util.JwtUtils;
import cn.com.zhejiangart.util.ThreadUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenVerifyInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(TokenVerifyInterceptor.class);
    
    @Resource
    private UserMapper userMapper;

    // Token剩余时间少于1小时时刷新
    private static final long REFRESH_THRESHOLD = 3600L;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String clientIP = getClientIP(request);
        
        logger.info("拦截器开始处理请求 - URI: {}, Method: {}, IP: {}", requestURI, method, clientIP);

        // 获取请求头中的token
        String token = request.getHeader("Token");
        if (token == null || token.isEmpty()) {
            logger.warn("请求缺少Authorization头 - URI: {}, IP: {}", requestURI, clientIP);
            response.setStatus(401);
            return false;
        }
        
        logger.debug("获取到token: {}", token.substring(0, Math.min(token.length(), 20)) + "...");
        
        try {
            // 验证token有效性
            Map<String, Object> m = JwtUtils.verifyToken(token);
            if (m == null) {
                logger.warn("Token验证失败 - URI: {}, IP: {}", requestURI, clientIP);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false; // 验证失败，中断请求
            }
            
            String userId = m.get("id").toString();
            logger.debug("Token验证成功，用户ID: {}", userId);
            
            User userName = userMapper.selectById(userId);
            if (userName == null) {
                logger.warn("用户不存在 - 用户ID: {}, URI: {}, IP: {}", userId, requestURI, clientIP);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            
            ThreadUtils.saveId("userId", userId);
            logger.info("用户验证成功 - 用户ID: {}, 用户名: {}, URI: {}", userId, userName, requestURI);
            return true;
            // 验证通过，继续执行后续操作
        } catch (Exception e) {
            logger.error("Token验证过程中发生异常 - URI: {}, IP: {}, 异常: {}", requestURI, clientIP, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false; // 验证失败，中断请求
        }
    }
    
    /**
     * 获取客户端真实IP地址
     */
    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIP = request.getHeader("X-Real-IP");
        if (xRealIP != null && !xRealIP.isEmpty() && !"unknown".equalsIgnoreCase(xRealIP)) {
            return xRealIP;
        }
        
        return request.getRemoteAddr();
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestURI = request.getRequestURI();
        int status = response.getStatus();
        
        logger.debug("请求处理完成 - URI: {}, 状态码: {}", requestURI, status);
        
        if (ex != null) {
            logger.error("请求处理过程中发生异常 - URI: {}, 异常: {}", requestURI, ex.getMessage(), ex);
        }
        
        ThreadUtils.clear();
        logger.debug("ThreadLocal已清理 - URI: {}", requestURI);
    }
}
