package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "CollectListDTO", description = "收藏列表DTO")
public class CollectListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "收藏ID")
    private String id;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "艺术家")
    private String artist;

    @Schema(description = "艺术家ID")
    private String artistId;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "数量")
    private String count;

    @Schema(description = "尺寸")
    private String goodRange;
}