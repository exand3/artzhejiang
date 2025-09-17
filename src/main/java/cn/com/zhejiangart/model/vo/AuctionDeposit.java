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
 * 拍卖保证金表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_deposit")
@Schema(name = "AuctionDeposit对象", description = "拍卖保证金表")
public class AuctionDeposit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "活动ID")
    private Integer activityId;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "保证金金额")
    private BigDecimal amount;

    @Schema(description = "0未缴纳,1已缴纳,2已退回,3已抵扣")
    private Boolean status;

    @Schema(description = "支付时间")
    private LocalDateTime paymentTime;

    @Schema(description = "退回时间")
    private LocalDateTime refundTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
