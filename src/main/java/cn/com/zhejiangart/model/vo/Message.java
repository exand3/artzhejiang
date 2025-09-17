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
 * 消息基础表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_message")
@Schema(name = "Message对象", description = "消息基础表")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "消息类型ID（关联message_type.id）")
    private Integer typeId;

    @Schema(description = "消息标题")
    private String title;

    @Schema(description = "消息内容（可存储模板化文本）")
    private String content;

    @Schema(description = "发送者ID（用户/系统账户）")
    private Long senderId;

    @Schema(description = "接收者ID")
    private Long receiverId;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "是否已读：0未读 1已读")
    private Boolean isRead;

    @Schema(description = "已读时间")
    private LocalDateTime readTime;

    @Schema(description = "状态：1有效 0失效")
    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Schema(description = "海报")
    private String image;

    @Schema(description = "活动id")
    private Integer activityId;


}
