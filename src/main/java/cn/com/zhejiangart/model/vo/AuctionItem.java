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
 * 拍卖品表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_item")
@Schema(name = "AuctionItem对象", description = "拍卖品表")
public class AuctionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "关联拍卖活动")
    private Integer auctionId;

    @Schema(description = "关联商品")
    private Integer productId;

    @Schema(description = "保留价")
    private BigDecimal reservePrice;

    @Schema(description = "起拍价")
    private BigDecimal startingPrice;

    @Schema(description = "加价幅度")
    private BigDecimal bidIncrement;

    @Schema(description = "当前价格")
    private BigDecimal currentPrice;

    @Schema(description = "当前最高出价者")
    private Integer currentWinnerId;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间（动态计算）")
    private LocalDateTime endTime;

    @Schema(description = "状态：1待审核，2已通过，3已拒绝，4已取消，5已结束")
    private String status;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "1预展，2已开始")
    private String sale;

    @Schema(description = "拒绝原因")
    private String refuseDesc;

    @Schema(description = "是否精选/1否/2是")
    private String isFeatured;

    @Schema(description = "作家")
    private Integer userId;

    @Schema(description = "拍卖时长")
    private Integer auctionTime;

    @Schema(description = "该拍品保证金")
    private BigDecimal deposit;


}
