package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.News;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

@Data
@Schema(name = "NewsDetailDTO", description = "新闻详情DTO")
public class NewsDetailDTO {

    @Schema(description = "新闻详情")
    private News news;
}