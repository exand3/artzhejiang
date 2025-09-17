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
 * 拍卖出价表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_bid")
@Schema(name = "AuctionBid对象", description = "拍卖出价表")
public class AuctionBid implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "活动id")
    private Integer activityId;

    @Schema(description = "作品id")
    private Integer productId;

    @Schema(description = "出价用户ID")
    private Integer userId;

    @Schema(description = "出价金额")
    private BigDecimal amount;

    @Schema(description = "出价时间")
    private LocalDateTime bidTime;

    @Schema(description = "号牌")
    private String plate;


}
