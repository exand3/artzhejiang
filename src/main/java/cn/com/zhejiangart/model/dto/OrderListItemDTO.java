package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "OrderListItemDTO", description = "订单列表项")
public class OrderListItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "作者（多个产品用分号分隔）")
    private String authors;

    @Schema(description = "尺寸（多个产品用分号分隔）")
    private String sizes;

    @Schema(description = "类别（多个产品用分号分隔）")
    private String categories;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "下单时间")
    private Integer createTime;

    @Schema(description = "订单状态")
    private String status;

    @Schema(description = "货品的第一个小图片")
    private String firstProductImage;
}