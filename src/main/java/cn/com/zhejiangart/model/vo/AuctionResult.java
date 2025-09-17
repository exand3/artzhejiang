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
 * 拍卖结果表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_result")
@Schema(name = "AuctionResult对象", description = "拍卖结果表")
public class AuctionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "拍卖品ID")
    private Integer auctionItemId;

    @Schema(description = "获胜者ID")
    private Integer winnerId;

    @Schema(description = "最终价格")
    private BigDecimal finalPrice;

    @Schema(description = "状态：pending, sold, unsold, canceled")
    private String status;

    @Schema(description = "卖家确认：0未确认，1已确认")
    private Boolean sellerConfirm;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
