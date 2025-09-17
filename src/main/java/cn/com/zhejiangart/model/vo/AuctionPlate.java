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
 * 拍卖号牌表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_auction_plate")
@Schema(name = "AuctionPlate对象", description = "拍卖号牌表")
public class AuctionPlate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "活动ID")
    private Integer activityId;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "号牌号码")
    private String plateNumber;

    @Schema(description = "状态：1有效，0无效，9 违规，8暂定")
    private Boolean status;

    private LocalDateTime createTime;

    @Schema(description = "即刻拍的活动id")
    private Integer productId;


}
