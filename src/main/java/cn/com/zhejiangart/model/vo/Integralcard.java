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
@TableName("ea8_integralcard")
@Schema(name = "Integralcard对象", description = "积分记录表")
public class Integralcard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "推广人")
    private Integer userId;

    @Schema(description = "积分")
    private BigDecimal integral;

    @Schema(description = "原因")
    private String resaion;

    @Schema(description = "0加，1减")
    private String status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
