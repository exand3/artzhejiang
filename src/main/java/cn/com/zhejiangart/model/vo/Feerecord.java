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
 * 积分记录表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_feerecord")
@Schema(name = "Feerecord对象", description = "积分记录表")
public class Feerecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "0余额，1积分,2保证金")
    private Integer type;

    @Schema(description = "变化的值")
    private BigDecimal count;

    @Schema(description = "原因")
    private String desc;

    @Schema(description = "0加，1扣除")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "用户id")
    private Integer userId;


}
