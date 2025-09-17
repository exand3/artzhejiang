package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(name = "UserProfileDTO", description = "用户个人信息DTO")
public class UserProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户类别ID")
    private String cateId;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "身份描述")
    private String identityDesc;

    @Schema(description = "认证状态: 0 未入驻，1 已入驻")
    private String identify;

    @Schema(description = "头像")
    private String avatar;
}