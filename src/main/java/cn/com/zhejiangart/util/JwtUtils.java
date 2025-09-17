package cn.com.zhejiangart.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Log
public class JwtUtils {
    // 默认过期时间（4小时）
    private static final long DEFAULT_EXPIRE = 14400L; // 4 * 60 * 60 = 14400秒

    // JWT密钥（建议从配置文件中读取）
    private static final String SECRET_KEY = "PiMdGURe1uXQXgrCPMPH9/HHoKRX2QMkzf/1Hos2nqbvJ4Kv+TcbXbjEXVT7jYiHFQkQQ6T4vBr6TOcBxLW7Lw==";

    // JWT算法
    private static final SignatureAlgorithm ALGO = SignatureAlgorithm.HS256;

    /**
     * 创建JWT令牌
     *
     * @param data 要存储在令牌中的数据
     * @return 生成的JWT令牌
     */
    public static String createToken(Map<String, Object> data) {
        return createToken(data, DEFAULT_EXPIRE);
    }

    /**
     * 创建JWT令牌
     *
     * @param data 要存储在令牌中的数据
     * @param expire 过期时间（秒）
     * @return 生成的JWT令牌
     */
    public static String createToken(Map<String, Object> data, long expire) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            long now = System.currentTimeMillis();
            long exp = now + expire * 1000;

            return Jwts.builder()
                    .setSubject("user")
                    .setIssuedAt(new Date(now))
                    .setExpiration(new Date(exp))
                    .claim("data", data)
                    .signWith(secretKey, ALGO)
                    .compact();
        } catch (Exception e) {
            log.warning("Token creation failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * 验证token（JWT）
     *
     * @param token token字符串
     * @return 令牌中的数据，如果验证失败返回null
     */
    public static Map<String, Object> verifyToken(String token) {
        try {
            if (token == null || token.isEmpty()) {
                log.warning("Token verification failed: Empty token");
                return null;
            }

            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            Claims claims = parser.parseClaimsJws(token).getBody();
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) claims.get("data");

            return data;
        } catch (JwtException | IllegalArgumentException e) {
            log.warning("Token verification failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * 获取令牌中的所有声明
     *
     * @param token token字符串
     * @return 令牌中的声明，如果验证失败返回null
     */
    public static Claims getClaims(String token) {
        try {
            if (token == null || token.isEmpty()) {
                log.warning("Get claims failed: Empty token");
                return null;
            }

            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            return parser.parseClaimsJws(token).getBody();
        } catch (JwtException | IllegalArgumentException e) {
            log.warning("Get claims failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * 检查令牌是否过期
     *
     * @param token token字符串
     * @return 如果令牌已过期返回true，否则返回false
     */
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaims(token);
            if (claims == null) {
                return true;
            }
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.warning("Check token expiration failed: " + e.getMessage());
            return true;
        }
    }
}
