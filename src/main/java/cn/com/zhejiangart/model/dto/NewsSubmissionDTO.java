package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 新闻投稿DTO
 */
@Data
@Schema(name = "NewsSubmissionDTO", description = "新闻投稿信息")
public class NewsSubmissionDTO {

    @Schema(description = "文章标题")
    private String name;

    @Schema(description = "所属分类")
    private Integer cateId;

    @Schema(description = "文章描述")
    private String introduce;

    @Schema(description = "关键词")
    private String keyword;

    @Schema(description = "新闻图片（小）")
    private String image;

    @Schema(description = "新闻详情图片")
    private String images;

    @Schema(description = "详情")
    private String detail;
}