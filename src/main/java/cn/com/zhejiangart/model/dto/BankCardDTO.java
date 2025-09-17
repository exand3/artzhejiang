package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 银行卡信息DTO
 */
@Data
public class BankCardDTO {

    @Schema(description = "银行卡ID")
    private Integer id;

    @Schema(description = "银行卡号")
    private String bankNo;

    @Schema(description = "银行名称")
    private String bankName;

    @Schema(description = "银行分行")
    private String bankBranch;

    @Schema(description = "持卡人姓名")
    private String username;

    @Schema(description = "预留手机号")
    private String telephone;

    @Schema(description = "创建时间")
    private Integer createAt;
}