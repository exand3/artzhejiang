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
 * 号牌订单
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_plate_order")
@Schema(name = "PlateOrder对象", description = "号牌订单")
public class PlateOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "号牌金额")
    private BigDecimal price;

    @Schema(description = "支付用户")
    private Integer userId;

    @Schema(description = "场次id")
    private Integer activityId;

    @Schema(description = "0微信，1支付宝，2银联")
    private String payType;

    @Schema(description = "0待付款，1已支付，2已退款，3已取消")
    private String status;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "创建订单时间")
    private LocalDateTime createTime;

    @Schema(description = "作品id")
    private Integer productId;


}
