package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
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
 * 订单项
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_orderitem")
@Schema(name = "Orderitem对象", description = "订单项")
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "画框价格")
    private BigDecimal framePrice;

    @Schema(description = "画框id")
    private Integer frameId;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "作品图")
    private String image;

    @Schema(description = "作品名")
    private String productName;


}
