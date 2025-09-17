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
 * 退款记录表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_tuikuan")
@Schema(name = "Tuikuan对象", description = "退款记录表")
public class Tuikuan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "普通订单，拍卖订单，店铺保证金，号牌费")
    private String type;

    @Schema(description = "退款单号")
    private String refundNo;

    @Schema(description = "微信，支付宝")
    private String paytype;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "成功/失败")
    private Boolean status;


}
