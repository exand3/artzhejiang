package cn.com.zhejiangart.controller.auth;

import cn.com.zhejiangart.model.vo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
     * 登录结果内部类
     */
@Data
@AllArgsConstructor
@NoArgsConstructor
   public class LoginResult {
    private boolean success;
    private String token;
    private User user;
    private String message;

}