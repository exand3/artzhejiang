package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 退款退货表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_returns")
@Schema(name = "Returns对象", description = "退款退货表")
public class Returns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "发货人账号id")
    private Integer faId;

    @Schema(description = "收货人账号id")
    private Integer shouId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "退货原因")
    private String returnReason;

    @Schema(description = "运费")
    private BigDecimal freightAmount;

    @Schema(description = "运费承担者（商家/用户）")
    private String freightPayer;

    @Schema(description = "物流公司")
    private String expressCompany;

    @Schema(description = "运单号")
    private String expressNumber;

    @Schema(description = "发货人手机号")
    private String faPhone;

    @Schema(description = "发货人地址")
    private String faProvince;

    @Schema(description = "发货人市")
    private String faCity;

    @Schema(description = "发货人区")
    private String faArea;

    @Schema(description = "发货人地址详情")
    private String faAddress;

    @Schema(description = "收货人手机号")
    private String shouPhone;

    @Schema(description = "收货人省")
    private String shouProvince;

    @Schema(description = "收货人市")
    private String shouCity;

    @Schema(description = "收货人区")
    private String shouArea;

    @Schema(description = "收货人地址")
    private String shouAddress;

    @Schema(description = "发货时间")
    private LocalDateTime faTime;

    @Schema(description = "收获时间")
    private LocalDateTime shouTime;

    @Schema(description = "0申请中，1审核通过，2已寄回")
    private Integer refundStatus;

    @Schema(description = "0完好，1破损，2数量不对，3其他原因")
    private Boolean merchantReceiveStatus;

    @Schema(description = "退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "平台操作人")
    private Integer adminId;

    @Schema(description = "保价金额")
    private BigDecimal baojia;

    @Schema(description = "0全部退，1部分退")
    private Integer refundType;

    @Schema(description = "用户申请时上传的图片凭证，json数组")
    private String applyImages;

    @Schema(description = "用户填写物流时上传的图片凭证，json数组")
    private String expressImages;

    @Schema(description = "平台审核备注")
    private String adminRemark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
