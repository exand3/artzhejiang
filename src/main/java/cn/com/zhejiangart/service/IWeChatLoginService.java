package cn.com.zhejiangart.service;

import cn.com.zhejiangart.controller.auth.LoginResult;
import cn.com.zhejiangart.model.vo.User;

/**
 * 微信登录服务接口
 */
public interface IWeChatLoginService {

    /**
     * 生成微信二维码
     * @return 二维码URL
     */
    String generateQRCode();

    /**
     * 检查登录状态
     * @param state 状态码
     * @return 用户信息和token
     */
    LoginResult checkLoginStatus(String state);

    /**
     * 处理微信回调
     * @param code 微信授权码
     * @param state 状态码
     * @return 登录结果
     */
    LoginResult handleWeChatCallback(String code, String state);


}