package cn.com.zhejiangart.model.po;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

@Data
@Schema(name = "NewsListPO", description = "新闻列表查询参数PO")
public class NewsListPO {

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "新闻分类ID", example = "1")
    private Integer cateId = 1;
}