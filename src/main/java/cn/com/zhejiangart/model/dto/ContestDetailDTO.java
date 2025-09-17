package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 大赛详情DTO
 */
@Data
@Schema(name = "ContestDetailDTO", description = "大赛详情信息")
public class ContestDetailDTO {

    @Schema(description = "大赛ID")
    private Integer id;

    @Schema(description = "期数")
    private Integer number;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "对该期的描述")
    private String contonts;

    @Schema(description = "开始时间")
    private Integer startTime;

    @Schema(description = "结束时间")
    private Integer endTime;

    @Schema(description = "收集作品结束时间")
    private Integer collectiontime;

    @Schema(description = "评选作品结束时间")
    private Integer selectiontime;

    @Schema(description = "承办方")
    private String contractor1;

    @Schema(description = "承办方图片")
    private String piccon1;

    @Schema(description = "承办方")
    private String contractor2;

    @Schema(description = "承办方图片")
    private String piccon2;
}