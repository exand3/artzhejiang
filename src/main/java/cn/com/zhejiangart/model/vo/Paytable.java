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
 * 各种审核支付表订单
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_paytable")
@Schema(name = "Paytable对象", description = "各种审核支付表订单")
public class Paytable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "下单用户")
    private Integer userId;

    @Schema(description = "支付类型：0微信，1支付宝，2余额")
    private Integer payType;

    @Schema(description = "金额")
    private BigDecimal price;

    @Schema(description = "0待付款，1已支付，2已退款，3已取消")
    private String status;

    @Schema(description = "订单类型：入驻保证金，升级身份等")
    private String type;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
