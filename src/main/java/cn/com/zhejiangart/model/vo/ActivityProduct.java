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
 * 活动关联的商品
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_activity_product")
@Schema(name = "ActivityProduct对象", description = "活动关联的商品")
public class ActivityProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "活动id")
    private Integer activityId;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "0待审核，1已通过，2未通过，3已取消申请")
    private Integer status;

    @Schema(description = "拒绝原因")
    private String refuseDesc;

    @Schema(description = "0待上架，1已上架")
    private Integer sale;

    @Schema(description = "是否精选/0否/1是")
    private Boolean isFeatured;


}
