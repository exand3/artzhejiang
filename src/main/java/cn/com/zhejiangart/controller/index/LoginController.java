package cn.com.zhejiangart.controller.index;

import cn.com.zhejiangart.mapper.UserMapper;
import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.util.JwtUtils;
import cn.com.zhejiangart.util.PasswordUtil;
import cn.com.zhejiangart.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 登录控制器
 */
@RestController
@RequestMapping("/api/login")
@Tag(name = "登录相关接口")
public class LoginController {

    @Resource
    private UserMapper userMapper;
    
    /**
     * 发送验证码
     */
    @PostMapping("/sendMsg")
    @Operation(summary = "发送验证码", description = "向指定手机号发送验证码")
    public Result<?> sendMsg(@Parameter(description = "手机号", required = true) @RequestBody Map<String, String> params) {
        try {
            String phone = params.get("phone");
            if (phone == null || phone.isEmpty()) {
                return Result.error(400, "手机号不能为空");
            }
            
            // 生成6位随机验证码
            String code = String.format("%06d", new Random().nextInt(999999));
            
            // TODO: 实际应该调用短信服务发送验证码
            // 这里只是模拟，实际项目中应该将验证码存储到Redis等缓存中，设置过期时间
            System.out.println("验证码已发送到手机号 " + phone + "，验证码是: " + code);
            
            Map<String, Object> data = new HashMap<>();
            data.put("code", code);
            data.put("phone", phone);
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "发送验证码失败：" + e.getMessage());
        }
    }
    
    /**
     * 密码登录
     */
    @PostMapping("/byPassword")
    @Operation(summary = "密码登录", description = "通过手机号和密码登录")
    public Result<?> loginByPassword(@Parameter(description = "登录参数", required = true) @RequestBody Map<String, String> loginParams) {
        try {
            String phone = loginParams.get("phone");
            String password = loginParams.get("password");
            
            if (phone == null || phone.isEmpty()) {
                return Result.error(400, "手机号不能为空");
            }
            
            if (password == null || password.isEmpty()) {
                return Result.error(400, "密码不能为空");
            }
            
            // 根据手机号查询用户
            User user = userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<User>().eq("mobile", phone));
            if (user == null) {
                return Result.error(400, "用户不存在");
            }
            
            // 验证密码（使用加密后的密码进行比较）
            if (!PasswordUtil.verify(password, user.getPassword())) {
                return Result.error(400, "密码错误");
            }
            
            // 更新登录时间
            user.setLogintime(System.currentTimeMillis() / 1000);
            userMapper.updateById(user);
            
            // 构建token数据
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("id", user.getId());
            tokenData.put("username", user.getNickname());
            
            // 生成token
            String token = JwtUtils.createToken(tokenData);
            
            // 构建返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user_id", user.getId());
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "登录失败：" + e.getMessage());
        }
    }
    
    /**
     * 验证码登录
     */
    @PostMapping("/byMsg")
    @Operation(summary = "验证码登录", description = "通过手机号和验证码登录")
    public Result<?> loginByMsg(@Parameter(description = "登录参数", required = true) @RequestBody Map<String, String> loginParams) {
        try {
            String phone = loginParams.get("phone");
            String msg = loginParams.get("msg");
            
            if (phone == null || phone.isEmpty()) {
                return Result.error(400, "手机号不能为空");
            }
            
            if (msg == null || msg.isEmpty()) {
                return Result.error(400, "验证码不能为空");
            }
            
            // 根据手机号查询用户
            User user = userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<User>().eq("mobile", phone));
            if (user == null) {
                return Result.error(400, "用户不存在");
            }
            
            // TODO: 实际应该从缓存中获取验证码并验证
            // 这里只是模拟验证，实际项目中应该从Redis等缓存中获取存储的验证码进行比较
            if (!"123456".equals(msg)) {
                return Result.error(400, "验证码错误");
            }
            
            // 更新登录时间
            user.setLogintime(System.currentTimeMillis() / 1000);
            userMapper.updateById(user);
            
            // 构建token数据
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("id", user.getId());
            tokenData.put("username", user.getNickname());
            
            // 生成token
            String token = JwtUtils.createToken(tokenData);
            
            // 构建返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user_id", user.getId());
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "登录失败：" + e.getMessage());
        }
    }
    
    /**
     * 刷新token
     */
    @PostMapping("/refreshToken")
    @Operation(summary = "刷新token", description = "使用旧token换取新token")
    public Result<?> refreshToken(@Parameter(description = "旧token", required = true) @RequestHeader("Token") String token) {
        try {
            // 验证旧token
            Map<String, Object> claims = JwtUtils.verifyToken(token);
            if (claims == null) {
                return Result.error(401, "无效的token");
            }
            
            // 生成新token（延长有效期）
            String newToken = JwtUtils.createToken(claims);
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", newToken);
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "刷新token失败：" + e.getMessage());
        }
    }
}