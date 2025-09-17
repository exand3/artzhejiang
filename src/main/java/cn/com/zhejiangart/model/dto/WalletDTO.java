package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "WalletDTO", description = "用户钱包信息DTO")
public class WalletDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "余额")
    private BigDecimal money;

    @Schema(description = "积分")
    private BigDecimal score;

    @Schema(description = "保证金")
    private BigDecimal deposit;
}