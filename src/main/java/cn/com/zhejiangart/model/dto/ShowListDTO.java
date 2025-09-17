package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 展览列表DTO
 */
@Data
@Schema(name = "ShowListDTO", description = "展览列表信息")
public class ShowListDTO {

    @Schema(description = "展览ID")
    private Integer id;

    @Schema(description = "展览标题")
    private String title;

    @Schema(description = "展览地点")
    private String address;

    @Schema(description = "展览概述")
    private String desc;

    @Schema(description = "展览海报")
    private String logo;

    @Schema(description = "票价")
    private BigDecimal price;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "展览状态: 0筹备中，1进行中，2已结束")
    private String status;

    @Schema(description = "审核状态: 0待审核，1已通过，2已拒绝")
    private String shenhe;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}