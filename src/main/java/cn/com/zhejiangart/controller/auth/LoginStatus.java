package cn.com.zhejiangart.controller.auth;

import cn.com.zhejiangart.model.vo.User;
import lombok.*;

/**
     * 登录状态内部类
     */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginStatus {
    private boolean loggedIn;
    private User user;
}