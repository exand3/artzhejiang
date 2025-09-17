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
 * 画框
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_frame")
@Schema(name = "Frame对象", description = "画框")
public class Frame implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "画框名")
    private String name;

    @Schema(description = "画框简介")
    private String desc;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "画框照片")
    private String image;

    @Schema(description = "创建时间")
    private Integer createTime;


}
