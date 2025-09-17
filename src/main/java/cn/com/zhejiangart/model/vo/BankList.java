package cn.com.zhejiangart.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_bank_list")
@Schema(name = "BankList对象")
public class BankList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    @Schema(description = "银行卡号")
    private String bankNo;

    @Schema(description = "银行名字")
    private String bankName;

    @Schema(description = "支行名字")
    private String bankBranch;

    @Schema(description = "户名")
    private String username;

    private String telephone;

    private Integer createAt;

    private Integer updateAt;


}
