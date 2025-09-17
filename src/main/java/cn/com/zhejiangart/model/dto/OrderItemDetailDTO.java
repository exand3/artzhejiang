package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "OrderItemDetailDTO", description = "订单项详情")
public class OrderItemDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品ID")
    private Integer productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品图片")
    private String image;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "尺寸")
    private String size;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "数量")
    private Integer count;
}