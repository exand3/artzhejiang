package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 地址信息DTO
 */
@Data
@Schema(name = "用户登录信息", description = "用于判断用户是否存在")
public class AddressDTO {

    @Schema(description = "地址ID")
    private Integer id;

    @Schema(description = "收货人姓名")
    private String nickname;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "县/区")
    private String area;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "是否默认地址(0默认，1非默认)")
    private String isUsually;

    @Schema(description = "创建时间")
    private Integer createTime;
}