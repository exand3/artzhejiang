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
 * 订单表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_order")
@Schema(name = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "下单用户")
    private Integer userId;

    @Schema(description = "收货人姓名")
    private String username;

    @Schema(description = "收货人手机号")
    private String userPhone;

    @Schema(description = "0微信，1支付宝，2银联,3余额，4收付通，5直付通")
    private String payType;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "消耗的积分")
    private BigDecimal score;

    @Schema(description = "积分抵扣金额")
    private BigDecimal scoreFee;

    @Schema(description = "0不开票，1开票")
    private String isInvoice;

    @Schema(description = "0个人，1企业")
    private String invoiceType;

    @Schema(description = "纳税识别号")
    private String invoiceCard;

    @Schema(description = "开票名称")
    private String invoiceName;

    @Schema(description = "接收发票邮箱")
    private String invoiceEmail;

    @Schema(description = "0待付款，1待发货，2已发货，3已签收，4已退款，5已取消")
    private String status;

    @Schema(description = "物流公司")
    private String expressCompany;

    @Schema(description = "物流单号")
    private String expressNumber;

    @Schema(description = "总价")
    private BigDecimal totalPrice;

    @Schema(description = "取消时间")
    private String deleteTime;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "更新时间")
    private Integer updateTime;

    @Schema(description = "商家id")
    private Integer author;

    @Schema(description = "收货人省")
    private String shouProvince;

    @Schema(description = "收货人市")
    private String shouCity;

    @Schema(description = "收货人区县")
    private String shouArea;

    @Schema(description = "收货人详细地址")
    private String shouAddress;

    @Schema(description = "发货人省")
    private String faProvince;

    @Schema(description = "发货人手机号")
    private String faPhone;

    @Schema(description = "发货人市")
    private String faCity;

    @Schema(description = "发货人区")
    private String faArea;

    @Schema(description = "发货人详细地址")
    private String faAddress;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "取消原因")
    private String cancelReason;

    @Schema(description = "订单类型id/活动id")
    private Integer orderTypeId;

    @Schema(description = "支付时间")
    private Integer payTime;

    @Schema(description = "发货时间")
    private Integer sendTime;

    @Schema(description = "完成时间")
    private Integer receiveTime;

    @Schema(description = "推广人id")
    private Integer tuiguangren;

    @Schema(description = "签收时间")
    private LocalDateTime signTime;

    @Schema(description = "退款时间")
    private LocalDateTime refundTime;


}
