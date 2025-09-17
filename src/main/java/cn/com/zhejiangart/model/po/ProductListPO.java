package cn.com.zhejiangart.model.po;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

@Data
@Schema(name = "ProductListPO", description = "艺术品列表查询参数PO")
public class ProductListPO {

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "二级类别ID")
    private Integer cateId;

    @Schema(description = "最低价格")
    private Double minPrice;

    @Schema(description = "最高价格")
    private Double maxPrice;

    @Schema(description = "尺寸")
    private String goodsRange;

    @Schema(description = "品相")
    private String goodsCom;

    @Schema(description = "创作年代")
    private Integer goodsCare;

    @Schema(description = "类型（0：租赁 1：购买）")
    private String isGroom;
}