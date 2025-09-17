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
 * 退款申请表
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_tuikuan_apply")
@Schema(name = "TuikuanApply对象", description = "退款申请表")
public class TuikuanApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer type;

    private String reason;

    private LocalDateTime createtime;

    private Integer status;

    private Integer userId;

    private String refuceText;

    @Schema(description = "  0 => '未退款',             1 => '已退款',")
    private Integer isRefund;

    private LocalDateTime refundTime;


}
