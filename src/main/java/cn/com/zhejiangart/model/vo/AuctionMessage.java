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
 * 拍卖消息扩展表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_message")
@Schema(name = "AuctionMessage对象", description = "拍卖消息扩展表")
public class AuctionMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    @Schema(description = "拍卖场景：号牌办理/最高价/胜出")
    private String auctionScene;

    @Schema(description = "关联拍卖活动ID")
    private Long auctionId;

    @Schema(description = "拍卖活动标题")
    private String auctionTitle;

    @Schema(description = "号牌编号（适用于号牌办理场景）")
    private String plateNumber;

    @Schema(description = "当前价格（适用于最高价场景）")
    private BigDecimal currentPrice;

    @Schema(description = "胜出者ID（适用于胜出场景）")
    private Long winnerId;

    @Schema(description = "胜出者名称")
    private String winnerName;


}
