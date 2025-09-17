package cn.com.zhejiangart.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动消息扩展表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_event_message")
@Schema(name = "EventMessage对象", description = "活动消息扩展表")
public class EventMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    @Schema(description = "关联活动ID")
    private Long eventId;

    @Schema(description = "活动标题")
    private String eventTitle;

    @Schema(description = "活动开始时间")
    private LocalDateTime eventStartTime;

    @Schema(description = "活动结束时间")
    private LocalDateTime eventEndTime;

    @Schema(description = "活动地点")
    private String eventLocation;


}
