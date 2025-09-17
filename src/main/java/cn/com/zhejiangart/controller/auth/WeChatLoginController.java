package cn.com.zhejiangart.controller.auth;

import cn.com.zhejiangart.service.IWeChatLoginService;
import cn.com.zhejiangart.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信登录控制器
 */
@RestController
@RequestMapping("/api/auth/wechat")
@Tag(name = "微信登录接口", description = "提供微信扫码登录相关功能")
public class WeChatLoginController {

    @Autowired
    private IWeChatLoginService weChatLoginService;

    /**
     * 获取微信登录二维码
     * @return 二维码URL
     */
    @GetMapping("/qrcode")
    @CrossOrigin(origins = "*")
    @Operation(summary = "获取微信登录二维码", description = "生成微信登录二维码")
    public Result<Map<String, String>> getQRCode() {
        String qrCodeUrl = weChatLoginService.generateQRCode();
        
        Map<String, String> result = new HashMap<>();
        result.put("qrCodeUrl", qrCodeUrl);
        
        return Result.success(result);
    }

    /**
     * 检查登录状态
     * @param state 状态码
     * @return 登录结果
     */
    @GetMapping("/check")
    @Operation(summary = "检查登录状态", description = "检查用户是否已完成微信登录")
    public Result<LoginResult> checkLoginStatus(
            @Parameter(description = "状态码") @RequestParam String state) {
        LoginResult result = weChatLoginService.checkLoginStatus(state);
        return Result.success(result);
    }

    /**
     * 微信回调接口
     * @param code 微信授权码
     * @param state 状态码
     * @return 登录结果
     */
    @GetMapping("/callback")
    @Operation(summary = "微信回调接口", description = "处理微信登录回调")
    public Result<LoginResult> handleCallback(
            @Parameter(description = "微信授权码") @RequestParam String code,
            @Parameter(description = "状态码") @RequestParam String state) {
        LoginResult result = weChatLoginService.handleWeChatCallback(code, state);
        return Result.success(result);
    }
}