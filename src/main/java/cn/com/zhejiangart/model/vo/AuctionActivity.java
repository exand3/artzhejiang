package cn.com.zhejiangart.model.vo;

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
 * 拍卖活动表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_activity")
@Schema(name = "AuctionActivity对象", description = "拍卖活动表")
public class AuctionActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "关联活动主表")
    private Integer activityId;

    @Schema(description = "拍卖类型：0普通拍，1出价就开拍")
    private String auctionType;

    @Schema(description = "自动延长时间(秒)")
    private Integer autoExtendTime;

    @Schema(description = "是否统一截拍0是1否")
    private Boolean isUnifiedEnd;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "拍卖时长（秒），只在出价就开拍场景使用")
    private Integer auctionTime;

    @Schema(description = "拍卖场编号")
    private String code;

    @Schema(description = "拍卖活动的名称")
    private String name;


}
