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
 * 申请入驻
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_apply")
@Schema(name = "Apply对象", description = "申请入驻")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "申请人id")
    private Integer userId;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "审核是否通过，0待审核，1已通过，2已拒绝")
    private String shenhe;

    @Schema(description = "身份证号")
    private String cardNumber;

    @Schema(description = "身份证正面")
    private String cardFront;

    @Schema(description = "身份证反面")
    private String cardBack;

    @Schema(description = "电子签名")
    private String cardNature;

    @Schema(description = "营业执照")
    private String cardImage;

    @Schema(description = "社会信用代码")
    private String cardCard;

    @Schema(description = "银行卡账号")
    private String bankCard;

    @Schema(description = "开户行")
    private String bankAddress;

    @Schema(description = "个人简介")
    private String identityDesc;

    @Schema(description = "申请的身份，1艺术家，2企业家，3学生")
    private String type;

    @Schema(description = "申请时间")
    private Integer applyTime;

    @Schema(description = "审核拒绝的原因")
    private String refuceText;

    @Schema(description = "专业")
    private Integer major;


}
