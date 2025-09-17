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
 * 活动主表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_activity")
@Schema(name="Activity对象", description="活动主表")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "活动名称")
    private String title;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "活动类型")
    private String activityType;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "删除时间")
    private Integer deleteTime;

    @Schema(description = "0待开始，1开始，2已结束，3已取消,4筹备中")
    private Integer status;

    @Schema(description = "创建者id")
    private Integer userId;

    @Schema(description = "宣传海报")
    private String image;


}
