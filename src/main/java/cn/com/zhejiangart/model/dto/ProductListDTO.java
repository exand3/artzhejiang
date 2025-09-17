package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ProductListDTO", description = "艺术品列表DTO")
public class ProductListDTO {

    @Schema(description = "艺术品列表")
    private List<Product> records;

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "每页数量")
    private Integer pageSize;

    @Schema(description = "总页数")
    private Integer totalPage;
}