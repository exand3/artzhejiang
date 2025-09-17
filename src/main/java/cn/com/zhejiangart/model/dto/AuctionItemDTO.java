package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(name = "AuctionItemDTO", description = "拍卖品信息")
public class AuctionItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "拍品ID")
    private Integer id;

    @Schema(description = "活动ID")
    private Integer activityId;

    @Schema(description = "拍品名称")
    private String name;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "尺寸")
    private String size;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "起拍价")
    private BigDecimal startingPrice;

    @Schema(description = "拍卖会开始时间")
    private LocalDateTime auctionStartTime;

    @Schema(description = "状态")
    private String status;
}