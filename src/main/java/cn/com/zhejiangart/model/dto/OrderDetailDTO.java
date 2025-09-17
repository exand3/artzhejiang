package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(name = "OrderDetailDTO", description = "订单详情")
public class OrderDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "订单状态")
    private String status;

    @Schema(description = "总价")
    private BigDecimal totalPrice;

    @Schema(description = "下单时间")
    private Integer createTime;

    @Schema(description = "订单项列表")
    private List<OrderItemDetailDTO> orderItems;
}