package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 展览申请DTO
 */
@Data
@Schema(name = "ShowApplicationDTO", description = "展览申请信息")
public class ShowApplicationDTO {

    @Schema(description = "展览标题")
    private String title;

    @Schema(description = "展览地点")
    private String address;

    @Schema(description = "展览概述")
    private String desc;

    @Schema(description = "个人简介")
    private String identifyDesc;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "个人作品")
    private String product;

    @Schema(description = "展览详情图片")
    private String images;

    @Schema(description = "票价")
    private BigDecimal price;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "每天闭馆时间")
    private String biguan;
}