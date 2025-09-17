package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 大赛列表DTO
 */
@Data
@Schema(name = "ContestListDTO", description = "大赛列表信息")
public class ContestListDTO {

    @Schema(description = "大赛ID")
    private Integer id;

    @Schema(description = "期数")
    private Integer number;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "图片")
    private String picn;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "开始时间")
    private Integer startTime;

    @Schema(description = "结束时间")
    private Integer endTime;
}