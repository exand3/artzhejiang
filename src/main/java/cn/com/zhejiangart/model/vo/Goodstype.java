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
 * 作品分类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_goodstype")
@Schema(name = "Goodstype对象", description = "作品分类")
public class Goodstype implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = " ")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "分类标题")
    private String name;

    @Schema(description = "简介")
    private String produce;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "修改时间")
    private Integer updateTime;

    @Schema(description = "背景图")
    private String image;

    @Schema(description = "0画，1创意")
    private Integer pid;


}
