package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 投稿作品DTO
 */
@Data
@Schema(name = "SubmissionWorkDTO", description = "投稿作品信息")
public class SubmissionWorkDTO {

    @Schema(description = "作品ID")
    private Integer id;

    @Schema(description = "真实姓名")
    private String author;

    @Schema(description = "身份证")
    private String sfid;

    @Schema(description = "作者简介")
    private String personDetail;

    @Schema(description = "出生日期")
    private LocalDateTime birth;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "作品名")
    private String wname;

    @Schema(description = "作品类型")
    private String wtype;

    @Schema(description = "尺寸")
    private String wsize;

    @Schema(description = "材质")
    private String wmaterial;

    @Schema(description = "作品简介")
    private String zuoDetail;

    @Schema(description = "作品图片1")
    private String wpic1;

    @Schema(description = "作品图片2")
    private String wpic2;

    @Schema(description = "作品图片3")
    private String wpic3;

    @Schema(description = "作品图片4")
    private String wpic4;

    @Schema(description = "作品图片5")
    private String wpic5;
}