package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 获奖作品DTO
 */
@Data
@Schema(name = "ContestWinningWorkDTO", description = "获奖作品信息")
public class ContestWinningWorkDTO {

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

    @Schema(description = "获奖名次")
    private Integer prizeStatus;

    @Schema(description = "作品类型")
    private String wtype;

    @Schema(description = "尺寸")
    private String wsize;
}