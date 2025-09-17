package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(name = "BankListDTO", description = "用户银行列表DTO")
public class BankListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "银行ID")
    private String id;

    @Schema(description = "银行卡号")
    private String bankNo;

    @Schema(description = "银行名称")
    private String bankName;

    @Schema(description = "开户行")
    private String bankBranch;

    @Schema(description = "持卡人姓名")
    private String username;

    @Schema(description = "联系电话")
    private String telephone;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}