package cn.com.zhejiangart.model.vo;

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
 * 审核消息扩展表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_audit_message")
@Schema(name = "AuditMessage对象", description = "审核消息扩展表")
public class AuditMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    @Schema(description = "审核类型：入驻/作品/资质等")
    private String auditType;

    @Schema(description = "审核状态：通过/拒绝/待审核")
    private String auditStatus;

    @Schema(description = "审核意见")
    private String auditOpinion;

    @Schema(description = "关联业务ID（如入驻申请ID、作品ID）")
    private Long relatedId;


}
