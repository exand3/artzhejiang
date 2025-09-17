package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "UpdatePasswordDTO", description = "更新密码DTO")
public class UpdatePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "新密码")
    private String newPassword;

    @Schema(description = "旧密码")
    private String oldPassword;

    @Schema(description = "确认密码")
    private String confirmPassword;
}