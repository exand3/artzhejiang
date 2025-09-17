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
 * 商品统计表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_product_stats")
@Schema(name = "ProductStats对象", description = "商品统计表")
public class ProductStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "商品ID")
    private Integer productId;

    @Schema(description = "浏览量")
    private Integer browser;

    @Schema(description = "收藏量")
    private Integer collection;

    @Schema(description = "点赞量")
    private Integer like;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "更新时间")
    private Integer updateTime;


}
