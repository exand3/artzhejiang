package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 参赛作品DTO
 */
@Data
@Schema(name = "ContestWorkDTO", description = "参赛作品信息")
public class ContestWorkDTO {

    @Schema(description = "作品ID")
    private Integer id;

    @Schema(description = "作品封面")
    private String picp;

    @Schema(description = "作品名")
    private String wname;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "投票数")
    private Integer counts;

    @Schema(description = "是否已投票标识")
    private Boolean voted = false;

    @Schema(description = "作品类型")
    private String wtype;

    @Schema(description = "尺寸")
    private String wsize;

    @Schema(description = "材质")
    private String wmaterial;

    @Schema(description = "作品简介")
    private String zuoDetail;
}