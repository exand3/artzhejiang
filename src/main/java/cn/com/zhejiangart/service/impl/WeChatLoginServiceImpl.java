package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.config.SensitiveConfig;
import cn.com.zhejiangart.controller.auth.LoginResult;
import cn.com.zhejiangart.controller.auth.LoginStatus;
import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.service.IWeChatLoginService;
import cn.com.zhejiangart.mapper.UserMapper;

import cn.com.zhejiangart.util.JwtUtils;
import cn.com.zhejiangart.util.PasswordUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 微信登录服务实现类
 */
@Service
public class WeChatLoginServiceImpl implements IWeChatLoginService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    // 从配置中读取微信appId
    private final String appId = SensitiveConfig.getWechatMpAppId();
    
    // 从配置中读取微信appSecret
    private final String appSecret = SensitiveConfig.getWechatMpSecret();

    // Redis中存储登录状态的前缀
    private static final String LOGIN_STATE_PREFIX = "wechat_login_state:";
    
    // Redis中存储用户信息的前缀
    private static final String USER_PREFIX = "user:";
    
    // 登录状态过期时间（分钟）
    private static final int LOGIN_STATE_EXPIRE = 5;

    @Override
    public String generateQRCode() {
        // 实际项目中需要调用微信API生成二维码
        // 这里仅作为示例返回模拟数据
        String state = java.util.UUID.randomUUID().toString();
        // 初始化登录状态到Redis
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(LOGIN_STATE_PREFIX + state, new LoginStatus(false, null), LOGIN_STATE_EXPIRE, TimeUnit.MINUTES);
        
        // 模拟微信二维码URL
        String redirectUri = "https://art.zhejiangart.com.cn/api/index/wxfallback";
        String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        return "https://open.weixin.qq.com/connect/qrconnect?appid=" + appId + "&redirect_uri=" + encodedRedirectUri + "&response_type=code&scope=snsapi_login&state=" + state;
    }

    @Override
    public LoginResult checkLoginStatus(String state) {
        // 实际项目中需要检查用户是否扫码并确认登录
        LoginResult result = new LoginResult();
        
        // 从Redis中获取登录状态
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        LoginStatus loginStatus = (LoginStatus) ops.get(LOGIN_STATE_PREFIX + state);
        
        if (loginStatus == null) {
            result.setSuccess(false);
            result.setMessage("登录请求已过期或无效");
            return result;
        }
        
        if (loginStatus.isLoggedIn()) {
            // 用户已登录
            result.setSuccess(true);
            result.setMessage("登录成功");
            result.setUser(loginStatus.getUser());
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("id", loginStatus.getUser().getId());
            tokenData.put("username", loginStatus.getUser().getNickname());
            result.setToken(JwtUtils.createToken(tokenData));
            
            // 清除已使用的状态记录
            redisTemplate.delete(LOGIN_STATE_PREFIX + state);
        } else {
            // 用户尚未登录
            result.setSuccess(false);
            result.setMessage("等待用户扫码确认");
        }
        
        return result;
    }

    @Override
    public LoginResult handleWeChatCallback(String code, String state) {
        // 实际项目中需要通过code获取access_token和用户信息
        // 'open_platform_app_id' => 'wx818945315b4b59e8'
        // 'open_platform_app_secret' => '6cb4f8698c10aaf04b6e253a445271ee'
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        
        LoginResult result = new LoginResult();
        
        try {
            // 构建获取access_token的URL
            String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + 
                                  "&secret=" + appSecret + 
                                  "&code=" + code + 
                                  "&grant_type=authorization_code";
            
            // 发送HTTP请求获取access_token和用户信息
            String response = sendGetRequest(accessTokenUrl);
            JSONObject jsonObject = JSONObject.parseObject(response);
            
            // 检查是否有错误
            if (jsonObject.containsKey("errcode")) {
                result.setSuccess(false);
                result.setMessage("微信授权失败: " + jsonObject.getString("errmsg"));
                return result;
            }
            
            // 获取access_token和openid
            String accessToken = jsonObject.getString("access_token");
            String unionid = jsonObject.getString("unionid");
            
            // 根据openid查询或创建用户
            User user = getUserByOpenid(unionid);
            if (user == null) {
                // 获取用户详细信息
                 user = getWeChatUserInfo(accessToken, unionid);
                // 创建新用户
                if (user == null) {
                   throw new RuntimeException("获取微信用户信息失败");
                }
                user.setUnionid(unionid);
                user.setPassword(PasswordUtil.password("123456"));
                if (!userMapper.insertOrUpdate(user)) {
                    throw new RuntimeException("创建新用户失败");
                }
            }
            user=user.cleanSensitiveInfo();
            result.setSuccess(true);
            result.setMessage("登录成功");
            result.setUser(user);

            // 更新Redis中的登录状态
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            LoginStatus loginStatus = (LoginStatus) ops.get(LOGIN_STATE_PREFIX + state);
            if (loginStatus != null) {
                loginStatus.setLoggedIn(true);
                loginStatus.setUser(user);
                ops.set(LOGIN_STATE_PREFIX + state, loginStatus, LOGIN_STATE_EXPIRE, TimeUnit.MINUTES);
            }
            
            // 将用户信息存储到Redis中
//            ops.set(USER_PREFIX + token, user, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("处理微信回调时发生错误: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 发送GET请求
     * @param url 请求URL
     * @return 响应内容
     * @throws Exception 请求异常
     */
    private String sendGetRequest(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        reader.close();
        return response.toString();
    }
    
    /**
     * 获取微信用户信息
     * @param accessToken access_token
     * @param openid openid
     * @return 用户信息
     */
    private User getWeChatUserInfo(String accessToken, String openid) {
        try {
            // 构建获取用户信息的URL
            String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                               "&openid=" + openid +
                               "&lang=zh_CN";
            
            // 发送HTTP请求获取用户信息
            String response = sendGetRequest(userInfoUrl);
            JSONObject jsonObject = JSONObject.parseObject(response);
            
            // 检查是否有错误
            if (jsonObject.containsKey("errcode")) {
                return null;
            }
            
            // 构造用户信息
            User user = new User();
            user.setOpenid(openid);
            user.setNickname(jsonObject.getString("nickname"));
            user.setAvatar(jsonObject.getString("headimgurl"));
            user.setGender("1".equals(jsonObject.getString("sex")) ? true : false);
            
            return user;
        } catch (Exception e) {
            // 发生异常时返回null
            return null;
        }
    }
    
    /**
     * 根据openid获取用户信息
     * @param openid 微信openid
     * @return 用户信息
     */
    private User getUserByOpenid(String openid) {
        // 实际项目中应查询数据库获取用户信息
        return userMapper.selectOne(new QueryWrapper<User>().eq("unionid", openid));
    }
}
