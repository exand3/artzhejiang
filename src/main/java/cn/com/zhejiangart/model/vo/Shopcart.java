package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
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
 * 购物车
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_shopcart")
@Schema(name = "Shopcart对象", description = "购物车")
public class Shopcart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "更新时间")
    private Integer updateTime;

    @Schema(description = "总价")
    private BigDecimal price;

    @Schema(description = "0未选，1选中")
    private Integer status;


}
