package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "AddBankDTO", description = "添加银行卡DTO")
public class AddBankDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
}